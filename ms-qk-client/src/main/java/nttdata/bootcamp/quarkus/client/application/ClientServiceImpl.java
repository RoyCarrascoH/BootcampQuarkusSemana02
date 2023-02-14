package nttdata.bootcamp.quarkus.client.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nttdata.bootcamp.quarkus.client.entity.Client;
import nttdata.bootcamp.quarkus.client.repository.ClientRepository;

import java.util.List;

@ApplicationScoped
public class ClientServiceImpl implements ClientService {
    @Inject
    ClientRepository clientRepository;

    @Override
    public List<Client> listAll() {
        return clientRepository.listAll();
    }

    @Override
    public Client findById(Long idClient) {
        return clientRepository.findById(idClient);
    }

    @Override
    public void save(Client client) {
        clientRepository.persist(client);
    }

    @Override
    public Client update(Long id, Client client) {
        clientRepository.persist(client);
        return client;
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

}
