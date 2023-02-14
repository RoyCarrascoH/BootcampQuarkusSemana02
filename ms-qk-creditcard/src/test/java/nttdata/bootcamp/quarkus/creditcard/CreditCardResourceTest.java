package nttdata.bootcamp.quarkus.creditcard;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import nttdata.bootcamp.quarkus.creditcard.dto.CreditCardResponse;
import nttdata.bootcamp.quarkus.creditcard.entity.CreditCardEntity;
import nttdata.bootcamp.quarkus.creditcard.repository.CreditCardRepository;
import nttdata.bootcamp.quarkus.creditcard.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CreditCardResourceTest {
        @Inject
        CreditCardResource cardResource;
        @InjectMock
        CreditCardService service;
        @InjectMock
        CreditCardRepository creditCardRepository;

        @Test
        public void testGetCreditCardNoExist() {
                Mockito.when(service.listAll()).thenReturn(new ArrayList<>());
                Mockito.when(creditCardRepository.listAll()).thenReturn(new ArrayList<>());
                CreditCardResponse clientResponse = cardResource.getCreditCard();
                System.out.println(clientResponse);
                assertEquals(1, clientResponse.getCodigoRespuesta());
        }

        @Test
        public void testGetCreditCardExist() {
                List<CreditCardEntity> creditCardEntities = new ArrayList<>();
                creditCardEntities.add(new CreditCardEntity());
                Mockito.when(service.listAll()).thenReturn(creditCardEntities);
                Mockito.when(creditCardRepository.listAll()).thenReturn(creditCardEntities);
                CreditCardResponse clientResponse = cardResource.getCreditCard();
                System.out.println(clientResponse);
                assertEquals(0, clientResponse.getCodigoRespuesta());
        }

        @Test
        public void testGetCreditCardNull() {
                Mockito.when(service.listAll()).thenReturn(null);
                Mockito.when(creditCardRepository.listAll()).thenReturn(null);
                CreditCardResponse clientResponse = cardResource.getCreditCard();
                System.out.println(clientResponse);
                assertEquals(2, clientResponse.getCodigoRespuesta());
        }
}