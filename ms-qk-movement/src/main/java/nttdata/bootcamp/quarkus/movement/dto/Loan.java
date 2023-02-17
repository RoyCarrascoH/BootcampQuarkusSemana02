package nttdata.bootcamp.quarkus.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private String loanNumber;//número de crédito
    private Double amountPaid;//monto pagado
    private Double currentBalance;//pendiente por pagar
    private Double initialBalance;//monto total
    private Integer numberQuotaPaid;//número de cuotas pagas
    private Integer numberQuotaPendient;//cuotas pendientes

}