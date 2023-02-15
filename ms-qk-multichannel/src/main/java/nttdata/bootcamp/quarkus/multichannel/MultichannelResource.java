package nttdata.bootcamp.quarkus.multichannel;

import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nttdata.bootcamp.quarkus.multichannel.application.MultichannelService;
import nttdata.bootcamp.quarkus.multichannel.entity.Multichannel;

@Path("/api/multichannel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MultichannelResource {

    @Inject
    MultichannelService multichannelService;

    @GET
    public Multi<Multichannel> list() {
        return multichannelService.streamAllPosts();
    }


}
