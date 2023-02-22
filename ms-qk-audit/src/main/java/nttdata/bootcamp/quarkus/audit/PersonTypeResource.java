package nttdata.bootcamp.quarkus.audit;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import nttdata.bootcamp.quarkus.audit.application.PersonTypeService;
import nttdata.bootcamp.quarkus.audit.entity.PersonType;

import java.util.List;

@Path("/api/person-type")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonTypeResource {

    @Inject
    PersonTypeService service;

    @GET
    public Uni<List<String>> keys() {
        return service.keys();
    }

    @POST
    public PersonType create(PersonType personType) {
        service.set(personType.key, personType.value);
        return personType;
    }

    @GET
    @Path("/{key}")
    public PersonType get(String key) {
        return new PersonType(key, service.get(key));
    }

    @PUT
    @Path("/{key}")
    public void increment(String key, Integer value) {
        service.personType(key, value);
    }

    @DELETE
    @Path("/{key}")
    public Uni<Void> delete(String key) {
        return service.del(key);
    }
}
