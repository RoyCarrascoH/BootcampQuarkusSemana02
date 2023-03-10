package nttdata.bootcamp.quarkus.movement.client;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import nttdata.bootcamp.quarkus.movement.entity.BankAccount;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/bank-account")
public interface BankAccountClient {

    @GET
    @Path("/numberBankAccount/{numberBankAccount}")
    public BankAccount viewBankAccountDetailsNumberBankAccount(@PathParam("numberBankAccount") String numberBankAccount);

    @PUT
    @Path("{idBankAccount}")
    @Transactional
    public BankAccount updateBankAccount(@PathParam("idBankAccount") Long idBankAccount, BankAccount bankAccount);

}
