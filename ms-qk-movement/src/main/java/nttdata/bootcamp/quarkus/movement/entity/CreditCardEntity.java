package nttdata.bootcamp.quarkus.movement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@Table(name = "creditcard")
public class CreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreditCard;
    private String descripcion;
    private Double creditLimit;
    private Double balanceAvailable;
    private String creditCardNumber;
    private Integer cvv;
    private Date expirationDate;
    private String closingDate;
    private String lastOfPay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClient")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;
}