package nttdata.bootcamp.quarkus.multichannel;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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

    @POST
//  @Blocking
    public Uni<Multichannel> create(Multichannel multichannel){
    	return multichannelService.saveMultichannel(multichannel);
    }
    
    @PUT
    public Uni<Multichannel> update(Multichannel multichannel){
    	multichannelService.updateMultichannel(multichannel);
    	return Uni.createFrom().item(multichannel);
    }
    
    @DELETE
    public Uni<Response> delete(Multichannel multichannel){
    	multichannelService.deleteMultichannel(multichannel);
    	return Uni.createFrom().item(Response.ok().build());
    }
    
    @GET
    @Path("/listproduct/{clientDocumentNumber}")
    public Uni<Response> listAllProductsByClient(@PathParam("clientDocumentNumber") String clientDocumentNumber) {
         return Uni.createFrom().item(null);
    }
    
    @GET
    @Path("/movementproduct/{numberProduct}")
    public Uni<Response> listMovementByNumberProduct(@PathParam("numberProduct") String numberProduct) {
        multichannelService.listMovementByNumberProduct(numberProduct);
        return Uni.createFrom().item(null); 
    }


}
