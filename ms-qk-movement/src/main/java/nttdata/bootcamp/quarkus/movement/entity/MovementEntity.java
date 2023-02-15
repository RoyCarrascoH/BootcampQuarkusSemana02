package nttdata.bootcamp.quarkus.movement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
@Table(name = "movements")
public class MovementEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovement;
    private int idTypeMovement;
    private String descriptionMovement;
    private String dateMovement;
    private double totalMovement;
    private int estateDelete;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCreditCard")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CreditCardEntity creditCard;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idLoan")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LoanEntity loan;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idDebitCard")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private DebitCard debitCard;
}