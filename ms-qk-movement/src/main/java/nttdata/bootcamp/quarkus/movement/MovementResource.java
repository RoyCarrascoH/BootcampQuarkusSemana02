package nttdata.bootcamp.quarkus.movement;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.movement.client.BankAccountClient;
import nttdata.bootcamp.quarkus.movement.client.CreditCardClient;
import nttdata.bootcamp.quarkus.movement.client.LoanClient;
import nttdata.bootcamp.quarkus.movement.dto.MovementResponse;
import nttdata.bootcamp.quarkus.movement.dto.MovementsByAccountNumber;
import nttdata.bootcamp.quarkus.movement.dto.ResponseBase;
import nttdata.bootcamp.quarkus.movement.entity.*;
import nttdata.bootcamp.quarkus.movement.service.MovementService;
import nttdata.bootcamp.quarkus.movement.util.Utility;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/movements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovementResource {
    private static final Logger LOGGER = Logger.getLogger(MovementResource.class.getName());
    @Inject
    private MovementService service;

    @RestClient
    CreditCardClient creditCardClient;

    @RestClient
    LoanClient loanClient;

    @RestClient
    BankAccountClient bankAccountClient;

    @GET
    public MovementResponse getMovements() {
        MovementResponse movementResponse = new MovementResponse();
        List<MovementEntity> movemnt = service.listAll();
        if (movemnt == null) {
            movementResponse.setCodigoRespuesta(2);
            movementResponse.setMensajeRespuesta("Respuesta nula");
            movementResponse.setMovement(null);
        } else if (movemnt.size() == 0) {
            movementResponse.setCodigoRespuesta(1);
            movementResponse.setMensajeRespuesta("No existen movimientos");
            movementResponse.setMovement(movemnt);
        } else {
            movementResponse.setCodigoRespuesta(0);
            movementResponse.setMensajeRespuesta("Respuesta Exitosa");
            movementResponse.setMovement(movemnt);
        }
        return movementResponse;
    }

    @GET
    @Path("{idMovement}")
    public MovementEntity viewMovementDetails(@PathParam("idMovement") Long idMovement) {
        MovementEntity entity = service.findById(idMovement);
        if (entity == null) {
            throw new WebApplicationException("Movement with id of " + idMovement + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(MovementEntity movement) {

        if (movement.getIdTypeMovement() == 1) {//Loan --> 1.- Pago de prestamo
            LoanEntity entity = loanClient.viewLoanDetails(movement.getLoan().getIdLoan());
            Double total = entity.getCurrentBalance() - movement.getTotalMovement();
            entity.setCurrentBalance(total);
            loanClient.updateLoan(entity.getIdLoan(), entity);
        } else if (movement.getIdTypeMovement() == 2) {// CreditCard --> 2.- Pago tarjeta de credito
            CreditCardEntity entity = creditCardClient.viewCreditCardDetails(movement.getCreditCard().getIdCreditCard());
            Double total = entity.getBalanceAvailable() + movement.getTotalMovement();
            entity.setBalanceAvailable(total);
            creditCardClient.updateCreditCard(entity.getIdCreditCard(), entity);
        } else if (movement.getIdTypeMovement() == 3) {// CreditCard --> 3.- Consumo
            CreditCardEntity entity = creditCardClient.viewCreditCardDetails(movement.getCreditCard().getIdCreditCard());
            Double total = entity.getBalanceAvailable() - movement.getTotalMovement();
            entity.setBalanceAvailable(total);
            creditCardClient.updateCreditCard(entity.getIdCreditCard(), entity);
        } else if (movement.getIdTypeMovement() == 4) {// DebitCard --> 4.- Retiro
            BankAccount entity = bankAccountClient.viewBankAccountDetailsNumberBankAccount(movement.getBankAccountNumber());
            Double total = entity.getAmount() - movement.getTotalMovement();
            entity.setAmount(total);
            bankAccountClient.updateBankAccount(entity.getIdBankAccount(), entity);
        } else if (movement.getIdTypeMovement() == 5) {// DebitCard --> 5.- Deposito
            BankAccount entity = bankAccountClient.viewBankAccountDetailsNumberBankAccount(movement.getBankAccountNumber());
            Double total = entity.getAmount() + movement.getTotalMovement();
            entity.setAmount(total);
            bankAccountClient.updateBankAccount(entity.getIdBankAccount(), entity);
        }

        service.save(movement);
        return Response.ok(movement).status(201).build();
    }

    @DELETE
    @Path("{idMovement}")
    @Transactional
    public ResponseBase delete(@PathParam("idMovement") Long idMovement) {
        ResponseBase response = new ResponseBase();
        MovementEntity entity = service.findById(idMovement);
        if (entity == null) {
            response.setCodigoRespuesta(1);
            response.setMensajeRespuesta("Id de movement no existe");
            throw new WebApplicationException("movement with id of " + idMovement + " does not exist.", 404);
        } else {
            response.setCodigoRespuesta(0);
            response.setMensajeRespuesta("Eliminacion exitosa de movement id = " + idMovement);
            MovementEntity.update("estateDelete", 1);
        }

        return response;
    }

    @PUT
    @Path("{idMovement}")
    @Transactional
    public MovementEntity updateMovement(@PathParam("idMovement") Long idMovement, MovementEntity movement) {

        MovementEntity entity = service.findById(idMovement);
        if (entity == null) {
            throw new WebApplicationException("Movement with id of " + idMovement + " does not exist.", 404);
        }
        entity.setIdTypeMovement(movement.getIdTypeMovement());
        entity.setDescriptionMovement(movement.getDescriptionMovement());
        entity.setDateMovement(movement.getDateMovement());
        entity.setTotalMovement(movement.getTotalMovement());
        entity.setBankAccountNumber(movement.getBankAccountNumber());
        entity.setCreditCardNumber(movement.getCreditCardNumber());
        entity.setLoanNumber(movement.getLoanNumber());
        entity.setCurrentBalance(movement.getCurrentBalance());
        entity.setEstateDelete(movement.getEstateDelete());
        service.save(entity);
        return entity;
    }

    @GET
    @Path("{bankAccountNumber}")
    public MovementsByAccountNumber findMovementsByAccountNumber(@PathParam("bankAccountNumber") String bankAccountNumber) {
        MovementsByAccountNumber response = new MovementsByAccountNumber();

        BankAccount bankAccount = bankAccountClient.viewBankAccountDetailsNumberBankAccount(bankAccountNumber);
        //BankAccount bankAccount = service.findCurrentBalance(bankAccountNumber);
        if (bankAccount == null) {
            throw new WebApplicationException("bankAccountNumber : " + bankAccountNumber + " does not exist Movements.", 404);
        }
        response = Utility.uploadBankAccount(bankAccount, response);

        List<MovementEntity> movements = service.findMovementsByAccountNumber(bankAccountNumber);
        if (movements == null) {
            throw new WebApplicationException("bankAccountNumber : " + bankAccountNumber + " does not exist in Movements.", 404);
        }
        response = Utility.uploadMovements(movements, response);

        return response;
    }

}