package nttdata.bootcamp.quarkus.debitcard.util;


import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.debitcard.entity.DebitCard;

@ApplicationScoped
public class Utilitarios {

    public static DebitCard saveDebitCard(DebitCard entity, DebitCard debitCard) {

        entity.setDebitCardNumber(debitCard.getDebitCardNumber());
        entity.setPin(debitCard.getPin());
        entity.setExpirationDate(debitCard.getExpirationDate());
        entity.setValidationCode(debitCard.getValidationCode());
        entity.setBankAccount(debitCard.getBankAccount());
        entity.setClient(debitCard.getClient());
        return entity;
    }

}