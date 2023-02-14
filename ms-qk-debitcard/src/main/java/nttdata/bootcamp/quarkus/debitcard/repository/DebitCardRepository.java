package nttdata.bootcamp.quarkus.debitcard.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.debitcard.entity.DebitCard;

@ApplicationScoped
public class DebitCardRepository implements PanacheRepository<DebitCard> {
}