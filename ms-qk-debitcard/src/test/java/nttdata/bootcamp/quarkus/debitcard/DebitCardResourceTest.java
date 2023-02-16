package nttdata.bootcamp.quarkus.debitcard;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import nttdata.bootcamp.quarkus.debitcard.application.DebitCardService;
import nttdata.bootcamp.quarkus.debitcard.dto.DebitCardResponse;
import nttdata.bootcamp.quarkus.debitcard.dto.ResponseBase;
import nttdata.bootcamp.quarkus.debitcard.entity.BankAccount;
import nttdata.bootcamp.quarkus.debitcard.entity.Client;
import nttdata.bootcamp.quarkus.debitcard.entity.DebitCard;
import nttdata.bootcamp.quarkus.debitcard.repository.DebitCardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class DebitCardResourceTest {

    @Inject
    DebitCardResource debitCardResource;

    @InjectMock
    DebitCardService service;

    @InjectMock
    DebitCardRepository debitCardRepository;

    @Test
    public void testGetDebitCardsNoExist() {
        Mockito.when(service.listAll()).thenReturn(new ArrayList<>());
        Mockito.when(debitCardRepository.listAll()).thenReturn(new ArrayList<>());
        DebitCardResponse debitCardResponse = debitCardResource.getDebitCards();
        System.out.println(debitCardResponse);
        assertEquals(1, debitCardResponse.getCodigoRespuesta());
    }

    @Test
    public void testGetDebitCardsExist() {
        List<DebitCard> debitCard = new ArrayList<>();
        debitCard.add(new DebitCard());
        Mockito.when(service.listAll()).thenReturn(debitCard);
        Mockito.when(debitCardRepository.listAll()).thenReturn(debitCard);
        DebitCardResponse debitCardResponse = debitCardResource.getDebitCards();
        System.out.println(debitCardResponse);
        assertEquals(0, debitCardResponse.getCodigoRespuesta());
    }

    @Test
    public void testGetDebitCardsNull() {
        Mockito.when(service.listAll()).thenReturn(null);
        Mockito.when(debitCardRepository.listAll()).thenReturn(null);
        DebitCardResponse debitCardResponse = debitCardResource.getDebitCards();
        System.out.println(debitCardResponse);
        assertEquals(2, debitCardResponse.getCodigoRespuesta());
    }

    @Test
    public void testFindADebitCardExist() {
        List<DebitCard> debitCards = new ArrayList<>();
        DebitCard debitCard = new DebitCard(Long.valueOf("1"), "123", 1234, LocalDate.now(), "123", true, new BankAccount(), new Client());
        debitCards.add(debitCard);
        Mockito.when(service.findById(debitCards.get(0).getIdDebitCard())).thenReturn(debitCard);
        Mockito.when(debitCardRepository.findById(debitCards.get(0).getIdDebitCard())).thenReturn(debitCard);
        assertEquals("123", debitCards.get(0).getDebitCardNumber());
    }

    @Test
    public void testFindADebitCardNull() {
        List<DebitCard> DebitCards = null;
        Mockito.when(service.findById(Long.valueOf(1))).thenReturn(null);
        Mockito.when(debitCardRepository.findById(Long.valueOf(1))).thenReturn(null);
        assertEquals(null, (Object) null);
    }

    @Test
    @Transactional
    public void testDeleteDebitCardNoExist() {
        List<DebitCard> DebitCards = new ArrayList<>();
        DebitCard debitCard = new DebitCard(Long.valueOf("1"), "123", 1234, LocalDate.now(), "123", true, new BankAccount(), new Client());
        DebitCards.add(debitCard);
        Mockito.when(service.findById(DebitCards.get(0).getIdDebitCard())).thenReturn(debitCard);
        Mockito.when(debitCardRepository.findById(DebitCards.get(0).getIdDebitCard())).thenReturn(debitCard);
        service.delete(DebitCards.get(0).getIdDebitCard());
        ResponseBase response = debitCardResource.getDebitCards();
        assertEquals(1, response.getCodigoRespuesta());
    }

}