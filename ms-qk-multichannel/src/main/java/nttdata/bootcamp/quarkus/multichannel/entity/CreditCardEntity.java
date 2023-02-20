package nttdata.bootcamp.quarkus.multichannel.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardEntity {
	
    private Long idCreditCard;
    private String descripcion;
    private Double creditLimit;
    private Double balanceAvailable;
    private String creditCardNumber;
    private Integer cvv;
    private Date expirationDate;
    private String closingDate;
    private String lastOfPay;
    private Client client;

}
