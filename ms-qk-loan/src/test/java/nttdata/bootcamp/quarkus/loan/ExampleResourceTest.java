package nttdata.bootcamp.quarkus.loan;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import nttdata.bootcamp.quarkus.loan.dto.LoanResponse;
import nttdata.bootcamp.quarkus.loan.entity.LoanEntity;
import nttdata.bootcamp.quarkus.loan.repository.LoanRepository;
import nttdata.bootcamp.quarkus.loan.service.LoanService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ExampleResourceTest {
    @Inject
    LoanResource loanResource;
    @InjectMock
    LoanService service;
    @InjectMock
    LoanRepository loanRepository;
    @Test
    public void testGetLoanNoExist() {
        Mockito.when(service.listAll()).thenReturn(new ArrayList<>());
        Mockito.when(loanRepository.listAll()).thenReturn(new ArrayList<>());
        LoanResponse loanResponse = loanResource.getLoans();
        System.out.println(loanResponse);
        assertEquals(1, loanResponse.getCodigoRespuesta());
    }
    @Test
    public void testGetLoanExist() {
        List<LoanEntity> loan = new ArrayList<>();
        loan.add(new LoanEntity());
        Mockito.when(service.listAll()).thenReturn(loan);
        Mockito.when(loanRepository.listAll()).thenReturn(loan);
        LoanResponse loanResponse = loanResource.getLoans();
        System.out.println(loanResponse);
        assertEquals(0, loanResponse.getCodigoRespuesta());
    }
    @Test
    public void testGetLoansNull() {
        Mockito.when(service.listAll()).thenReturn(null);
        Mockito.when(loanRepository.listAll()).thenReturn(null);
        LoanResponse loanResponse = loanResource.getLoans();
        System.out.println(loanResponse);
        assertEquals(2, loanResponse.getCodigoRespuesta());
    }
}