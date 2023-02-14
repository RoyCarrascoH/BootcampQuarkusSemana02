package nttdata.bootcamp.quarkus.creditcard.entity;

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
@Table(name = "creditcard")
public class CreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreditCard;
    private String descripcion;
    private double creditLimit;
    private double balanceAvailable;
    private String creditCardNumber;
    private int cvv;
    private String expirationDate;
    private String closingDate;
    private String lastOfPay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClient")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;
}