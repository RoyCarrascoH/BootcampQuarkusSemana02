package nttdata.bootcamp.quarkus.client.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.client.entity.Client;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client>{
}