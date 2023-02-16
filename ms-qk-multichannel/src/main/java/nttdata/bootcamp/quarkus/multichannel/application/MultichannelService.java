package nttdata.bootcamp.quarkus.multichannel.application;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.multichannel.entity.Multichannel;

public interface MultichannelService {

    public Multi<Multichannel> streamAllPosts();
    
    public Uni<Multichannel> saveMultichannel(Multichannel multichannel);
    
    public Uni<Multichannel> updateMultichannel(Multichannel multichannel);
    
    public Uni<Multichannel> deleteMultichannel(Multichannel multichannel);
    
    public Uni<Response> listAllProductsByClient(String clientDocumentNumber);
    
    public Uni<Response> listMovementByNumberProduct(String numberProduct);

}
