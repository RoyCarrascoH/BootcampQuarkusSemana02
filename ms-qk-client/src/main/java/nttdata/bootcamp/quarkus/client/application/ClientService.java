package nttdata.bootcamp.quarkus.client.application;

import nttdata.bootcamp.quarkus.client.entity.Client;
import java.util.List;

public interface ClientService
{

    public List<Client> listAll();

    public Client findById(Long id);

    public void save(Client client);

    public Client update(Long id, Client client);

    public void delete(Long id);
}
