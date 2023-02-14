package nttdata.bootcamp.quarkus.bankaccount;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.bankaccount.application.BankAccountService;
import nttdata.bootcamp.quarkus.bankaccount.dto.BankAccountResponse;
import nttdata.bootcamp.quarkus.bankaccount.dto.ResponseBase;
import nttdata.bootcamp.quarkus.bankaccount.entity.BankAccount;
import nttdata.bootcamp.quarkus.bankaccount.util.Utilitarios;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/bank-account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BankAccountResource {

    private static final Logger LOGGER = Logger.getLogger(BankAccountResource.class.getName());

    @Inject
    private BankAccountService service;

    @GET
    @Timeout(180)
    public BankAccountResponse getBankAccounts() {
        BankAccountResponse bankAccountsResponse = new BankAccountResponse();
        List<BankAccount> bankAccounts = service.listAll();
        if (bankAccounts == null) {
            bankAccountsResponse.setCodigoRespuesta(2);
            bankAccountsResponse.setMensajeRespuesta("Respuesta nula");
            bankAccountsResponse.setBankAccounts(null);
        } else if (bankAccounts.size() == 0) {
            bankAccountsResponse.setCodigoRespuesta(1);
            bankAccountsResponse.setMensajeRespuesta("No existen cuentas bancarias");
            bankAccountsResponse.setBankAccounts(bankAccounts);
        } else {
            bankAccountsResponse.setCodigoRespuesta(0);
            bankAccountsResponse.setMensajeRespuesta("Respuesta Exitosa");
            bankAccountsResponse.setBankAccounts(bankAccounts);
        }
        return bankAccountsResponse;
    }

    @GET
    @Path("{idBankAccount}")
    @Retry(maxRetries = 4)
    public BankAccount viewBankAccountDetails(@PathParam("idBankAccount") Long idBankAccount) {
        BankAccount entity = service.findById(idBankAccount);
        if (entity == null) {
            throw new WebApplicationException("BankAccount with id of " + idBankAccount + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response createBankAccount(BankAccount bankAccount) {
        if (bankAccount.getIdBankAccount() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        service.save(bankAccount);
        return Response.ok(bankAccount).status(200).build();
    }

    @PUT
    @Path("{idBankAccount}")
    @Transactional
    public BankAccount updateBankAccount(@PathParam("idBankAccount") Long idBankAccount, BankAccount bankAccount) {
        if (bankAccount.getNumberAccount() == null) {
            throw new WebApplicationException("BankAccount number account was not set on request.", 422);
        }
        BankAccount entity = service.findById(idBankAccount);
        if (entity == null) {
            throw new WebApplicationException("BankAccount with id of " + idBankAccount + " does not exist.", 404);
        }
        entity = Utilitarios.saveBankAccount(entity, bankAccount);
        service.update(idBankAccount, entity);
        return entity;
    }

    @DELETE
    @Path("{idBankAccount}")
    @Transactional
    public ResponseBase delete(@PathParam("idBankAccount") Long idBankAccount) {
        ResponseBase response = new ResponseBase();
        BankAccount entity = service.findById(idBankAccount);
        if (entity == null) {
            response.setCodigoRespuesta(1);
            response.setMensajeRespuesta("Id de BankAccount no existe");
            throw new WebApplicationException("BankAccount with id of " + idBankAccount + " does not exist.", 404);
        } else {
            response.setCodigoRespuesta(0);
            response.setMensajeRespuesta("Eliminacion exitosa de BankAccount id = " + idBankAccount);
            service.delete(entity.getIdBankAccount());
        }
        return response;
    }

    @PUT
    @Path("/updateMainAccount/{idBankAccount}/mainAccount/{mainAccount}")
    @Transactional
    public BankAccount updateMainAccount(@PathParam("idBankAccount") Long idBankAccount, @PathParam("mainAccount") String mainAccount) {
        BankAccount entity = service.findById(idBankAccount);
        if (entity == null) {
            throw new WebApplicationException("BankAccount with id of " + idBankAccount + " does not exist.", 404);
        }
        entity = Utilitarios.saveUpdateMainAccount(entity, mainAccount);
        service.update(idBankAccount, entity);
        return entity;
    }

}