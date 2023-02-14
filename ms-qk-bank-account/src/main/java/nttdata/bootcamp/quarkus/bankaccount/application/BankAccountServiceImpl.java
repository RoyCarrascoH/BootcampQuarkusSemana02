package nttdata.bootcamp.quarkus.bankaccount.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nttdata.bootcamp.quarkus.bankaccount.entity.BankAccount;
import nttdata.bootcamp.quarkus.bankaccount.repository.BankAccountRepository;
import java.util.List;

@ApplicationScoped
public class BankAccountServiceImpl implements BankAccountService {

    @Inject
    BankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccount> listAll() {
        return bankAccountRepository.listAll();
    }

    @Override
    public BankAccount findById(Long idBankAccount) {
        return bankAccountRepository.findById(idBankAccount);
    }

    @Override
    public void save(BankAccount client) {
        bankAccountRepository.persist(client);
    }

    @Override
    public BankAccount update(Long idBankAccount, BankAccount bankAccount) {
        bankAccountRepository.persist(bankAccount);
        return bankAccount;
    }

    @Override
    public void delete(Long idBankAccount) {
        bankAccountRepository.deleteById(idBankAccount);
    }
}
