package nttdata.bootcamp.quarkus.multichannel.application;

import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.multichannel.entity.Multichannel;

@ApplicationScoped
public class MultichannelServiceImpl implements MultichannelService {
    public Multi<Multichannel> streamAllPosts() {
        return Multichannel.streamAll();
    }

}
