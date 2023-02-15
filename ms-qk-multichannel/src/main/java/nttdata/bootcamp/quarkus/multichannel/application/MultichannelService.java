package nttdata.bootcamp.quarkus.multichannel.application;

import io.smallrye.mutiny.Multi;
import nttdata.bootcamp.quarkus.multichannel.entity.Multichannel;

public interface MultichannelService {

    public Multi<Multichannel> streamAllPosts();

}
