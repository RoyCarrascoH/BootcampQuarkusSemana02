package nttdata.bootcamp.quarkus.multichannel.proxy;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import nttdata.bootcamp.quarkus.multichannel.entity.LoanResponse;

public interface LoanApi {
	
	@GET
    @Path("/documentNumber/{documentNumber}")
    public LoanResponse findLoanByDocumentNumber(@PathParam("documentNumber") String documentNumber);

}
