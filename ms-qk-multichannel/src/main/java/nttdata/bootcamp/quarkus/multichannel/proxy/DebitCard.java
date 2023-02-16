package nttdata.bootcamp.quarkus.multichannel.proxy;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Path;

@RegisterRestClient
@Path("/api/debit-card")
public interface DebitCard {

}
