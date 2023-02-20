package nttdata.bootcamp.quarkus.client.proxy;

import io.smallrye.mutiny.Uni;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.client.dto.Audit;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/audit")
public interface AuditClient {

    @POST
    @Transactional
    public Response create(Audit audit);
}
