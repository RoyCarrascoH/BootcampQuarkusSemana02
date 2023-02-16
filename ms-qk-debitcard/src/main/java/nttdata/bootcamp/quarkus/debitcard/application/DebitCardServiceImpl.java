package nttdata.bootcamp.quarkus.debitcard.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nttdata.bootcamp.quarkus.debitcard.entity.DebitCard;
import nttdata.bootcamp.quarkus.debitcard.repository.DebitCardRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    	debitCard.setStatus(true);
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

	@Override
	public boolean validateDataAndStatusDebitCard(String debitCardNumber, String expirationDate,
			String validationCode) {
		DateTimeFormatter current = DateTimeFormatter.ofPattern("ddMMyyyy");
		DateTimeFormatter wanted = DateTimeFormatter.ofPattern("yyyy-dd-MM");
		LocalDate expirationDateFormat=LocalDate.parse(wanted.format(current.parse(expirationDate)));
		DebitCard debitCardResult=DebitCard.find("debitCardNumber = ?1 and validationCode = ?2 and status=true", debitCardNumber, validationCode).firstResult();
		if(debitCardResult!=null) {
			return debitCardResult.getExpirationDate().equals(expirationDateFormat);	
		}
		return false;
		
	}

	@Override
	public boolean validateDebitCardAndClientData(String debitCardNumber, String documentType, String documentNumber) {
		DebitCard debitCardResult = DebitCard.find("debitCardNumber = ?1 and client.documentType = ?2 and client.documentNumber = ?3", debitCardNumber, documentType, documentNumber).firstResult();
		return debitCardResult!=null?debitCardResult.count()>0:false;
	}

}
