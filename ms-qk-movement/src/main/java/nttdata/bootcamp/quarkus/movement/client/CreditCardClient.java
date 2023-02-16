package nttdata.bootcamp.quarkus.movement.client;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import nttdata.bootcamp.quarkus.movement.entity.CreditCardEntity;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/creditcard")
public interface CreditCardClient {

    @PUT
    @Path("{idCreditCard}")
    @Transactional
    CreditCardEntity updateCreditCard(@PathParam("idCreditCard") Long idCreditCard, CreditCardEntity creditCard);

    @GET
    @Path("{idCreditCard}")
    CreditCardEntity viewCreditCardDetails(@PathParam("idCreditCard") Long idCreditCard);
}
