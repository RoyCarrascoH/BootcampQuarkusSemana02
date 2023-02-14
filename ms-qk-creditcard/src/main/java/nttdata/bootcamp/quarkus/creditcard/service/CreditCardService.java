package nttdata.bootcamp.quarkus.creditcard.service;

import nttdata.bootcamp.quarkus.creditcard.entity.CreditCardEntity;

import java.util.List;
public interface CreditCardService {
    public List<CreditCardEntity> listAll();
    public CreditCardEntity findById(Long id);
    public void save(CreditCardEntity creditCardEntity);
    public CreditCardEntity update(Long id, CreditCardEntity creditCardEntity);
    public void delete(Long id);
}