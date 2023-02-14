package nttdata.bootcamp.quarkus.movement;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.movement.client.CreditCardClient;
import nttdata.bootcamp.quarkus.movement.dto.MovementResponse;
import nttdata.bootcamp.quarkus.movement.dto.ResponseBase;
import nttdata.bootcamp.quarkus.movement.entity.CreditCardEntity;
import nttdata.bootcamp.quarkus.movement.entity.MovementEntity;
import nttdata.bootcamp.quarkus.movement.service.MovementService;
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
    public Response create(MovementEntity movement) {

        if (movement.getIdTypeMovement() == 3) {
            CreditCardEntity entity = creditCardClient.viewCreditCardDetails(movement.getCreditCard().getIdCreditCard());
            double total = entity.getBalanceAvailable() - movement.getTotalMovement();
            entity.setBalanceAvailable(total);
            creditCardClient.updateCreditCard(entity.getIdCreditCard(), entity);
        } else if (movement.getIdTypeMovement() == 2) {
            CreditCardEntity entity = creditCardClient.viewCreditCardDetails(movement.getCreditCard().getIdCreditCard());
            double total = entity.getBalanceAvailable() + movement.getTotalMovement();
            entity.setBalanceAvailable(total);
            creditCardClient.updateCreditCard(entity.getIdCreditCard(), entity);
        }

        service.save(movement);
        return Response.ok(movement).status(201).build();
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
            service.delete(entity.getIdMovement());
        }

        return response;
    }

    @PUT
    @Path("{idMovent}")
    @Transactional
    public MovementEntity updateMovement(@PathParam("idMovent") Long idMovent, MovementEntity movent) {

        MovementEntity entity = service.findById(idMovent);
        if (entity == null) {
            throw new WebApplicationException("Movent with id of " + idMovent + " does not exist.", 404);
        }
        entity.setIdTypeMovement(movent.getIdTypeMovement());
        entity.setDescriptionMovement(movent.getDescriptionMovement());
        entity.setDateMovement(movent.getDateMovement());
        entity.setTotalMovement(movent.getTotalMovement());
        entity.setEstateDelete(movent.getEstateDelete());
        service.save(entity);

        return entity;
    }
}