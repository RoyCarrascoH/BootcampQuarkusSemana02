package nttdata.bootcamp.quarkus.bankaccount.util;

import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.bankaccount.entity.BankAccount;

@ApplicationScoped
public class Utilitarios {

    public static BankAccount saveBankAccount(BankAccount entity, BankAccount bankAccount) {

        entity.setNumberAccount(bankAccount.getNumberAccount());
        entity.setOpeningDate(bankAccount.getOpeningDate());
        entity.setDescription(bankAccount.getDescription());
        entity.setAmount(bankAccount.getAmount());
        entity.setMainAccount(bankAccount.getMainAccount());
        return entity;
    }

    public static BankAccount saveUpdateMainAccount(BankAccount entity, String mainAccount) {

        entity.setNumberAccount(entity.getNumberAccount());
        entity.setOpeningDate(entity.getOpeningDate());
        entity.setDescription(entity.getDescription());
        entity.setAmount(entity.getAmount());
        entity.setMainAccount(mainAccount);
        return entity;
    }

}
