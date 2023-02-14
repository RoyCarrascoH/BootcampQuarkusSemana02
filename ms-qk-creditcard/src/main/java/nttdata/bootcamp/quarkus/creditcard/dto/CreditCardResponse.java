package nttdata.bootcamp.quarkus.creditcard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nttdata.bootcamp.quarkus.creditcard.entity.CreditCardEntity;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardResponse extends ResponseBase{
    private List<CreditCardEntity> creditCard;

}
