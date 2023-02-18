package nttdata.bootcamp.quarkus.multichannel.proxy;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient
@Path("/api/creditcard")
public interface CreditCardApi {
	/*
	@GET
    @Path("/listcredit/{documentNumber}")
    public List<CreditCardEntity> listCreditCardByDocumentNumber(@PathParam("documentNumber") String documentNumber);
	
	@GET
    @Path("/creditCardNumber/{creditCardNumber}")
    public CreditCardEntity viewCreditCardDetailsCreditCardNumber(@PathParam("creditCardNumber") String creditCardNumber);
*/
}
