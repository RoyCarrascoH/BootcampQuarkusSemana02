package nttdata.bootcamp.quarkus.audit;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.net.URI;
import java.time.LocalDate;

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
    public Multi<Audit> list() {
        return auditService.streamAllPosts();
    }

    @POST
    @Transactional
    public Uni<Response> create(Audit audit) {
        audit.setRegistrationDate(LocalDate.now());
        return audit.<Audit>persist().map(v ->
                Response.created(URI.create("/api/audit/" + v.getIdAudit()))
                        .entity(audit).build());
    }

    @PUT
    @Path("{idAudit}")
    @Transactional
    public Uni<Audit> update(@PathParam("idAudit") String idAudit, Audit audit) {
        return auditService.updateAudit(idAudit, audit);
    }

    @DELETE
    @Path("{idAudit}")
    @Transactional
    public Uni<Response> delete(@PathParam("idAudit") String idAudit, Audit audit) {
        auditService.deleteAudit(idAudit);
        return Uni.createFrom().item(Response.ok().build());
    }

}