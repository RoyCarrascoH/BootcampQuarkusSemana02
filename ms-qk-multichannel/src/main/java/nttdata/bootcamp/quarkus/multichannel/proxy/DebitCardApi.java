package nttdata.bootcamp.quarkus.multichannel.proxy;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@RegisterRestClient
@Path("/api/debit-card")
public interface DebitCardApi {
	
	@GET
    @Path("/validate/{debitCardNumber}/{expirationDate}/{validationCode}")
    public Response validateDataAndStatusDebitCard(@PathParam("debitCardNumber") String debitCardNumber,@PathParam("expirationDate") String expirationDate,@PathParam("validationCode") String validationCode);
	
	@GET
    @Path("/validateclient/{debitCardNumber}/{documentType}/{documentNumber}")
    public Response validateDebitCardAndClientData(@PathParam("debitCardNumber") String debitCardNumber,@PathParam("documentType") String documentType, @PathParam("documentNumber") String documentNumber);
	
	@GET
    @Path("/validatepin/{debitCardNumber}/{pin}")
    public Response validateDebitCardAndPin(@PathParam("debitCardNumber") String debitCardNumber,@PathParam("pin") String pin);
	
	@GET
    @Path("/searchlist/{documentNumber}")
    public Response searchListDebitCardByDocumentNumber(@PathParam("documentNumber") String documentNumber);

}
