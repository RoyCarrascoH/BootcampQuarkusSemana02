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
public class MovementsByAccountNumber extends ResponseBase {

    private BankAccount bankAccount;
    private List<Movement> movements;
}
