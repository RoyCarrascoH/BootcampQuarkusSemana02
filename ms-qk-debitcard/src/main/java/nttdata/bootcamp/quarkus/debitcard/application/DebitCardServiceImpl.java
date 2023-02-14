package nttdata.bootcamp.quarkus.debitcard.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nttdata.bootcamp.quarkus.debitcard.entity.DebitCard;
import nttdata.bootcamp.quarkus.debitcard.repository.DebitCardRepository;
import java.util.List;

@ApplicationScoped
public class DebitCardServiceImpl implements DebitCardService {
    @Inject
    DebitCardRepository debitCardRepository;

    @Override
    public List<DebitCard> listAll() {
        return debitCardRepository.listAll();
    }

    @Override
    public DebitCard findById(Long idClient) {
        return debitCardRepository.findById(idClient);
    }

    @Override
    public void save(DebitCard debitCard) {
        debitCardRepository.persist(debitCard);
    }

    @Override
    public DebitCard update(Long idDebitCard, DebitCard debitCard) {
        debitCardRepository.persist(debitCard);
        return debitCard;
    }

    @Override
    public void delete(Long idDebitCard) {
        debitCardRepository.deleteById(idDebitCard);
    }

}
