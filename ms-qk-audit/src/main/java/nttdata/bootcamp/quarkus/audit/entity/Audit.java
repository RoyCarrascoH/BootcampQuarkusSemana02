package nttdata.bootcamp.quarkus.audit.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@MongoEntity(collection = "audit")
public class Audit extends ReactivePanacheMongoEntityBase {

    @BsonProperty("_id")
    @BsonId
    private ObjectId idAudit;
    private String idTransaccion;
    private String user;
    private LocalDate registrationDate;
    private String request;
    private String response;
    private Integer codigoRespuesta;
    private String mensajeRespuesta;

}
