package nttdata.bootcamp.quarkus.movement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@Table(name = "debitcard")
public class DebitCard extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDebitCard;
    private String debitCardNumber;
    private Integer pin;
    private LocalDate expirationDate;
    private String validationCode;
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)//Esta propiedad se utiliza para determinar cómo debe ser cargada la entidad
    @JoinColumn(name = "idBankAccount")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//deserializa para procesarlo
    private BankAccount bankAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClient")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

}
