package nttdata.bootcamp.quarkus.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementsByLoanNumber extends ResponseBase {

    private Loan loan;
    private List<Movement> movements;
}
