package nttdata.bootcamp.quarkus.bankaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase {

    private Integer codigoRespuesta;
    private String mensajeRespuesta;

}
