package nttdata.bootcamp.quarkus.debitcard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nttdata.bootcamp.quarkus.debitcard.entity.DebitCard;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebitCardResponse extends ResponseBase {

    private List<DebitCard> debitCards;

}
