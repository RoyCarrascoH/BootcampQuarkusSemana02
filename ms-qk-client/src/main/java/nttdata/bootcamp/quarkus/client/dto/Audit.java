package nttdata.bootcamp.quarkus.client.dto;

//import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Audit {

    //private ObjectIdGenerator idAudit;
    private String idTransaccion;
    private String user;
    private LocalDate registrationDate;
    private String request;
    private String response;
    private Integer codigoRespuesta;
    private String mensajeRespuesta;

}
