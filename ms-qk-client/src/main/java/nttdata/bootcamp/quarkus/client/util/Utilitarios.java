package nttdata.bootcamp.quarkus.client.util;

import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.client.entity.Client;

@ApplicationScoped
public class Utilitarios {

    public static Client saveClient(Client entity, Client client) {

        entity.setPersonType(client.getPersonType());
        entity.setDocumentType(client.getDocumentType());
        entity.setDocumentNumber(client.getDocumentNumber());
        entity.setCompleteName(client.getCompleteName());
        entity.setSurnames(client.getSurnames());
        entity.setSex(client.getSex());
        entity.setEmail(client.getEmail());
        entity.setCellPhone(client.getCellPhone());

        return entity;
    }

}
