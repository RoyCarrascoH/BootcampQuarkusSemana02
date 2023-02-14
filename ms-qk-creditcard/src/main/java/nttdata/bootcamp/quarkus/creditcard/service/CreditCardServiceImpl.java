package nttdata.bootcamp.quarkus.creditcard.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nttdata.bootcamp.quarkus.creditcard.entity.CreditCardEntity;
import nttdata.bootcamp.quarkus.creditcard.repository.CreditCardRepository;

import java.util.List;
@ApplicationScoped
public class CreditCardServiceImpl implements CreditCardService{
    @Inject
    CreditCardRepository creditCardRepository;
    @Override
    public List<CreditCardEntity> listAll() {
        return creditCardRepository.listAll();
    }

    @Override
    public CreditCardEntity findById(Long id) {
        return creditCardRepository.findById(id);
    }

    @Override
    public void save(CreditCardEntity creditCardEntity) {
            creditCardRepository.persist(creditCardEntity);
    }

    @Override
    public CreditCardEntity update(Long id, CreditCardEntity creditCardEntity) {
        creditCardRepository.persist(creditCardEntity);
        return creditCardEntity;
    }

    @Override
    public void delete(Long id) {
        creditCardRepository.deleteById(id);
    }
}
