package nttdata.bootcamp.quarkus.audit;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.audit.entity.Audit;
import nttdata.bootcamp.quarkus.audit.application.AuditService;

@Path("/api/audit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuditResource {

    @Inject
    AuditService auditService;

    @GET
    public Multi<Audit> listAudit() {
        return auditService.streamAllPosts();
    }

    /*@POST
    public Uni<Audit> create(Audit audit) {
        auditService.saveAudit(audit);
        return Uni.createFrom().item(audit);
    }*/

    @POST
    public Uni<Response> createAudit(Audit audit) {
        audit.registrationDate = LocalDate.now();
        return audit.<Audit>persist().map(v ->
                Response.created(URI.create("/api/audit/" + v.idAudit.toString()))
                        .entity(audit).build());
    }

    @PUT
    public Uni<Audit> update(Audit audit) {
        auditService.updateAudit(audit);
        return Uni.createFrom().item(audit);
    }

    @DELETE
    public Uni<Response> delete(Audit audit) {
        auditService.deleteAudit(audit);
        return Uni.createFrom().item(Response.ok().build());
    }
}
