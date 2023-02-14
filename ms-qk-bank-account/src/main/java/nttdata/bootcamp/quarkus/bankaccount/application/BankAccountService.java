package nttdata.bootcamp.quarkus.bankaccount.application;

import nttdata.bootcamp.quarkus.bankaccount.entity.BankAccount;
import java.util.List;

public interface BankAccountService {

    public List<BankAccount> listAll();

    public BankAccount findById(Long idBankAccount);

    public void save(BankAccount bankAccount);

    public BankAccount update(Long id, BankAccount bankAccount);

    public void delete(Long idBankAccount);

}
