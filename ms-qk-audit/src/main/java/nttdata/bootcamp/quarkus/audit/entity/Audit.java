package nttdata.bootcamp.quarkus.audit.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MongoEntity(collection = "audit")
public class Audit extends ReactivePanacheMongoEntityBase {

    @BsonId
    public Long idAudit;
    private String idTransaccion;
    private String user;
    @BsonIgnore
    public LocalDate registrationDate;
    private String request;
    private String response;
    private Integer codigoRespuesta;
    private String mensajeRespuesta;

}
