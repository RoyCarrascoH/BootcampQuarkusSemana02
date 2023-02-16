package nttdata.bootcamp.quarkus.multichannel.application;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.multichannel.entity.Multichannel;

@ApplicationScoped
public class MultichannelServiceImpl implements MultichannelService {
  
	@Override
	public Multi<Multichannel> streamAllPosts() {
		// TODO Auto-generated method stub
		return Multichannel.streamAll();
	}

	@Override
	public Uni<Multichannel> saveMultichannel(Multichannel multichannel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uni<Multichannel> updateMultichannel(Multichannel multichannel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uni<Multichannel> deleteMultichannel(Multichannel multichannel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uni<Response> listAllProductsByClient(String clientDocumentNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uni<Response> listMovementByNumberProduct(String numberProduct) {
		// TODO Auto-generated method stub
		return null;
	}

}
