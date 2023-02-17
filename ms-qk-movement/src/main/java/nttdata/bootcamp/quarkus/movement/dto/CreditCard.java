package nttdata.bootcamp.quarkus.movement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nttdata.bootcamp.quarkus.movement.entity.Client;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    private String creditCardNumber;
    private Double creditLimit;
    private Double balanceAvailable;
    private Double usedCredit;//Calcular (creditLimit-balanceAvailable)
    private String closingDate;

}