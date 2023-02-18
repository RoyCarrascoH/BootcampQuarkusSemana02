package nttdata.bootcamp.quarkus.multichannel.application;

import java.time.format.DateTimeFormatter;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.multichannel.entity.Multichannel;
import nttdata.bootcamp.quarkus.multichannel.proxy.DebitCardApi;

@ApplicationScoped
public class MultichannelServiceImpl implements MultichannelService {
	
	@Inject
	@RestClient
	DebitCardApi debitCardApi;
  
	@Override
	public Multi<Multichannel> streamAllPosts() {
		// TODO Auto-generated method stub
		return Multichannel.streamAll();
	}

	@Override
	public Uni<Multichannel> saveMultichannel(Multichannel multichannel) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		String formattedExpirationDate = multichannel.getExpirationDate().format(formatter);
		try {
			Response response1=debitCardApi.validateDataAndStatusDebitCard(multichannel.getDebitCardNumber(), formattedExpirationDate, multichannel.getValidationCode());
			if(response1.readEntity(Boolean.class)) {
				Response response2=debitCardApi.validateDebitCardAndClientData(multichannel.getDebitCardNumber(), multichannel.getDocumentType(), multichannel.getDocumentNumber());
				if(response2.readEntity(Boolean.class)) {
					Response response3=debitCardApi.validateDebitCardAndPin(multichannel.getDebitCardNumber(), multichannel.getPin().toString());
					if(response3.readEntity(Boolean.class)){
						Multichannel.persist(multichannel);
						return Uni.createFrom().item(multichannel);
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		throw new WebApplicationException("No cumple condicion");
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
