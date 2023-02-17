package nttdata.bootcamp.quarkus.movement.client;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import nttdata.bootcamp.quarkus.movement.entity.LoanEntity;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/loan")
public interface LoanClient {

    @GET
    @Path("{idLoan}")
    public LoanEntity viewLoanDetails(@PathParam("idLoan") Long idLoan);

    @PUT
    @Path("{idLoan}")
    @Transactional
    public LoanEntity updateLoan(@PathParam("idLoan") Long idLoan, LoanEntity loan);

    @GET
    @Path("/loanNumber/{loanNumber}")
    public LoanEntity viewLoanDetailsLoanNumber(@PathParam("loanNumber") String loanNumber);
}
