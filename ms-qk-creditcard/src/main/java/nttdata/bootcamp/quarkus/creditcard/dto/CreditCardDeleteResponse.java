package nttdata.bootcamp.quarkus.creditcard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDeleteResponse extends ResponseBase{

    private Long idClient;
}
