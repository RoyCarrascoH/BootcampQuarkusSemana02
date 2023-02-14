package nttdata.bootcamp.quarkus.creditcard.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.creditcard.entity.CreditCardEntity;

@ApplicationScoped
public class CreditCardRepository implements PanacheRepository<CreditCardEntity> {
}