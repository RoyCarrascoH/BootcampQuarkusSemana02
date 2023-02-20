package nttdata.bootcamp.quarkus.client.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.quarkus.vertx.http.runtime.devmode.Json;
import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.client.dto.Audit;
import nttdata.bootcamp.quarkus.client.entity.Client;

import java.time.LocalDate;

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

    public static Audit saveAudit(String idTransaccion, String user, Client client,
                                  Integer cod, String msj, String e) throws JsonProcessingException {

        Audit audit = new Audit();
        ObjectWriter gson = new ObjectMapper().writer().withDefaultPrettyPrinter();

        audit.setIdTransaccion(idTransaccion);
        audit.setUser(user);
        audit.setRegistrationDate(LocalDate.now());
        audit.setRequest(gson.writeValueAsString(client));
        audit.setResponse(e);
        audit.setCodigoRespuesta(cod);
        audit.setMensajeRespuesta(msj);
        return audit;
    }

}
