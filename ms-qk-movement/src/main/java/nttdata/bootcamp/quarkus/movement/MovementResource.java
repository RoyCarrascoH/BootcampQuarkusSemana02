package nttdata.bootcamp.quarkus.movement;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.movement.client.BankAccountClient;
import nttdata.bootcamp.quarkus.movement.client.CreditCardClient;
import nttdata.bootcamp.quarkus.movement.client.LoanClient;
import nttdata.bootcamp.quarkus.movement.dto.*;
import nttdata.bootcamp.quarkus.movement.entity.*;
import nttdata.bootcamp.quarkus.movement.entity.BankAccount;
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
    public ResponseBase create(MovementEntity movement) {

        ResponseBase response = new ResponseBase();
        if (movement.getIdTypeMovement() == 1) {//Loan --> 1.- Pago de prestamo

            LoanEntity entity = loanClient.viewLoanDetails(movement.getLoan().getIdLoan());
            if (movement.getTotalMovement().equals(entity.getQuotaAmount())) {
                LOGGER.info(movement.getTotalMovement());
                LOGGER.info(entity.getQuotaAmount());
                Double total = entity.getCurrentBalance() - movement.getTotalMovement();
                entity.setCurrentBalance(total);
                entity.setAmountOfFeesPaid(entity.getAmountOfFeesPaid() + 1);
                loanClient.updateLoan(entity.getIdLoan(), entity);
            } else {
                response.setCodigoRespuesta(1);
                response.setMensajeRespuesta("Monto a pagar no es igual a monto de cuota");
                return response;
            }
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
        response.setCodigoRespuesta(0);
        response.setMensajeRespuesta("Creado con exito");
        return response;
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
            MovementEntity.update("estateDelete =?1 where idMovement =?2", 1, entity.getIdMovement());
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
    @Path("/findMovementsByAccountNumber/{bankAccountNumber}")
    public MovementsByAccountNumber findMovementsByAccountNumber(@PathParam("bankAccountNumber") String bankAccountNumber) {
        MovementsByAccountNumber response = new MovementsByAccountNumber();

        BankAccount bankAccount = bankAccountClient.viewBankAccountDetailsNumberBankAccount(bankAccountNumber);
        if (bankAccount == null) {
            response.setCodigoRespuesta(-1);
            response.setMensajeRespuesta("Cuentas bancarias es nulo");
            throw new WebApplicationException("bankAccountNumber : " + bankAccountNumber + " does not exist Movements.", 404);
        }
        response = Utility.uploadBankAccount(bankAccount, response);

        List<MovementEntity> movements = service.findMovementsByAccountNumber(bankAccountNumber);
        if (movements == null) {
            response.setCodigoRespuesta(-1);
            response.setMensajeRespuesta("Consulta sin movimientos");
            throw new WebApplicationException("bankAccountNumber : " + bankAccountNumber + " does not exist in Movements.", 404);
        } else if (movements.size() == 0) {
            response.setCodigoRespuesta(1);
            response.setMensajeRespuesta("Consulta no tiene movimientos");
            return response;
        }
        response = Utility.uploadMovementsBankAccount(movements, response);
        response.setCodigoRespuesta(0);
        response.setMensajeRespuesta("Consulta Exitosa");

        return response;
    }

    @GET
    @Path("/findMovementsByCreditCardNumber/{creditCardNumber}")
    public MovementsByCreditCardNumber findMovementsByCreditCardNumber(@PathParam("creditCardNumber") String creditCardNumber) {
        MovementsByCreditCardNumber response = new MovementsByCreditCardNumber();

        CreditCardEntity creditCard = creditCardClient.viewCreditCardDetailsCreditCardNumber(creditCardNumber);
        if (creditCard == null) {
            response.setCodigoRespuesta(-1);
            response.setMensajeRespuesta("Tarjeta de credito es nulo");
            throw new WebApplicationException("creditCardNumber : " + creditCardNumber + " does not exist Movements.", 404);
        }
        response = Utility.uploadCreditCard(creditCard, response);

        List<MovementEntity> movements = service.findMovementsByCreditCardNumber(creditCardNumber);
        if (movements == null) {
            response.setCodigoRespuesta(-1);
            response.setMensajeRespuesta("Consulta sin movimientos");
            throw new WebApplicationException("creditCardNumber : " + creditCardNumber + " does not exist in Movements.", 404);
        } else if (movements.size() == 0) {
            response.setCodigoRespuesta(1);
            response.setMensajeRespuesta("Consulta no tiene movimientos");
            return response;
        }
        response = Utility.uploadMovementsCreditCard(movements, response);
        response.setCodigoRespuesta(0);
        response.setMensajeRespuesta("Consulta Exitosa");

        return response;
    }

    @GET
    @Path("/findMovementsByLoanNumber/{loanNumber}")
    public MovementsByLoanNumber findMovementsByLoanNumber(@PathParam("loanNumber") String loanNumber) {

        MovementsByLoanNumber response = new MovementsByLoanNumber();
        LoanEntity loan = loanClient.viewLoanDetailsLoanNumber(loanNumber);
        if (loan == null) {
            response.setCodigoRespuesta(-1);
            response.setMensajeRespuesta("Préstamo no existe");
            throw new WebApplicationException("loanNumber : " + loanNumber + " does not exist Movements.", 404);
        }
        response = Utility.uploadLoan(loan, response);

        List<MovementEntity> movements = service.findMovementsByLoanNumber(loanNumber);
        if (movements == null) {
            response.setCodigoRespuesta(-1);
            response.setMensajeRespuesta("Consulta sin movimientos");
            throw new WebApplicationException("loanNumber : " + loanNumber + " does not exist in Movements.", 404);
        } else if (movements.size() == 0) {
            response.setCodigoRespuesta(1);
            response.setMensajeRespuesta("Consulta no tiene movimientos");
            return response;
        }
        response = Utility.uploadMovementsLoan(movements, response);
        response.setCodigoRespuesta(0);
        response.setMensajeRespuesta("Consulta Exitosa");

        return response;
    }

}