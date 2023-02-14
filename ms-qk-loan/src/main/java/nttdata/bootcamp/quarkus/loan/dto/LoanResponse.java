package nttdata.bootcamp.quarkus.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nttdata.bootcamp.quarkus.loan.entity.LoanEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse extends ResponseBase {
    private List<LoanEntity> loan;
}
