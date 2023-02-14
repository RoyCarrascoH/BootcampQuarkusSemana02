package nttdata.bootcamp.quarkus.client;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.client.application.ClientService;
import nttdata.bootcamp.quarkus.client.dto.ClientResponse;
import nttdata.bootcamp.quarkus.client.dto.ResponseBase;
import nttdata.bootcamp.quarkus.client.entity.Client;
import nttdata.bootcamp.quarkus.client.util.Utilitarios;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private static final Logger LOGGER = Logger.getLogger(ClientResource.class.getName());

    @Inject
    private ClientService service;

    @GET
    @Timeout(250)
    public ClientResponse getClients() {
        ClientResponse clientsResponse = new ClientResponse();
        List<Client> clients = service.listAll();
        if(clients==null){
            clientsResponse.setCodigoRespuesta(2);
            clientsResponse.setMensajeRespuesta("Respuesta nula");
            clientsResponse.setClients(null);
        }
        else if(clients.size()==0){
            clientsResponse.setCodigoRespuesta(1);
            clientsResponse.setMensajeRespuesta("No existen clientes");
            clientsResponse.setClients(clients);
        }else{
            clientsResponse.setCodigoRespuesta(0);
            clientsResponse.setMensajeRespuesta("Respuesta Exitosa");
            clientsResponse.setClients(clients);
        }
        return clientsResponse;
    }

    @GET
    @Path("{idClient}")
    @Retry(maxRetries = 4)
    public Client viewClientDetails(@PathParam("idClient") Long idClient) {
        Client entity = service.findById(idClient);
        if (entity == null) {
            throw new WebApplicationException("Client with id of " + idClient + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Client client) {
        if (client.getIdClient() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        service.save(client);
        return Response.ok(client).status(201).build();
    }

    @PUT
    @Path("{idClient}")
    @Transactional
    public Client updateClient(@PathParam("idClient") Long idClient, Client client) {
        if (client.getPersonType() == null) {
            throw new WebApplicationException("Client person type was not set on request.", 422);
        }
        Client entity = service.findById(idClient);
        if (entity == null) {
            throw new WebApplicationException("Client with id of " + idClient + " does not exist.", 404);
        }

        entity = Utilitarios.saveClient(entity, client);
        service.update(idClient,entity);
        return entity;
    }

    @DELETE
    @Path("{idClient}")
    @Transactional
    public ResponseBase delete(@PathParam("idClient") Long idClient) {
        ResponseBase response = new ResponseBase();
        Client entity = service.findById(idClient);
        if (entity == null) {
            response.setCodigoRespuesta(1);
            response.setMensajeRespuesta("Id de cliente no existe");
            throw new WebApplicationException("Client with id of " + idClient + " does not exist.", 404);
        } else {
            response.setCodigoRespuesta(0);
            response.setMensajeRespuesta("Eliminacion exitosa de cliente id = " + idClient);
            service.delete(entity.getIdClient());
        }

        return response;
    }

}