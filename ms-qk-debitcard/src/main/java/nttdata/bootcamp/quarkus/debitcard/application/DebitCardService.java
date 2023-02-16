package nttdata.bootcamp.quarkus.debitcard.application;

import nttdata.bootcamp.quarkus.debitcard.entity.DebitCard;

import java.util.List;

public interface DebitCardService
{

    public List<DebitCard> listAll();

    public DebitCard findById(Long idDebitCard);

    public void save(DebitCard debitCard);

    public DebitCard update(Long idDebitCard, DebitCard debitCard);

    public void delete(Long idDebitCard);
    
    public boolean validateDataAndStatusDebitCard(String debitCardNumber, String expirationDate, String validationCode);
    
    public boolean validateDebitCardAndClientData(String debitCardNumber, String documentType, String documentNumber);
    
    public boolean validatDebitCardAndPin(String debitCardNumber, String pin);
}
