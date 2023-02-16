package nttdata.bootcamp.quarkus.loan.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@Table(name = "loans")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLoan;
    private String loanNumber;
    private String monthlyPaymentDate;
    private String expirationDate;
    private Integer quotaNumber;
    private String validationCode;
    private Double initialBalance;
    private Double currentBalance;
    private Integer amountOfFeesPaid;
    private Double quotaAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClient")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;
}