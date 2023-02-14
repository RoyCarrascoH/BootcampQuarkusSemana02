package nttdata.bootcamp.quarkus.bankaccount.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.bankaccount.entity.BankAccount;

@ApplicationScoped
public class BankAccountRepository implements PanacheRepository<BankAccount> {
}