package nttdata.bootcamp.quarkus.multichannel.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection="multichannel")
public class Multichannel extends ReactivePanacheMongoEntityBase {

	@BsonProperty("_id")
    @BsonId
    private ObjectId idMultichannel;
    private String debitCardNumber;
    private LocalDate expirationDate;
    private String validationCode;
    private String documentType;
    private String documentNumber;
    private String passwordMultichannel;
    private Integer pin;

}
