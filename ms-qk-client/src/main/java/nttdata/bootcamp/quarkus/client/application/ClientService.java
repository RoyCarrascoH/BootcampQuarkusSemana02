package nttdata.bootcamp.quarkus.client.application;

import nttdata.bootcamp.quarkus.client.entity.Client;
import java.util.List;

public interface ClientService
{

    List<Client> listAll();

    Client findById(Long id);

    void save(Client client);

    Client update(Long id, Client client);

    void delete(Long id);

}
