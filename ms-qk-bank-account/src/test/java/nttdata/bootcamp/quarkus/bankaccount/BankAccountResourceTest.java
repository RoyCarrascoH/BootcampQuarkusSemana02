package nttdata.bootcamp.quarkus.bankaccount;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import nttdata.bootcamp.quarkus.bankaccount.application.BankAccountService;
import nttdata.bootcamp.quarkus.bankaccount.dto.BankAccountResponse;
import nttdata.bootcamp.quarkus.bankaccount.dto.ResponseBase;
import nttdata.bootcamp.quarkus.bankaccount.entity.BankAccount;
import nttdata.bootcamp.quarkus.bankaccount.repository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BankAccountResourceTest {

    @Inject
    BankAccountResource bankAccountResource;

    @InjectMock
    BankAccountService service;

    @InjectMock
    BankAccountRepository bankAccountRepository;

    @Test
    public void testGetBankAccountsNoExist() {
        Mockito.when(service.listAll()).thenReturn(new ArrayList<>());
        Mockito.when(bankAccountRepository.listAll()).thenReturn(new ArrayList<>());
        BankAccountResponse bankAccountResponse = bankAccountResource.getBankAccounts();
        System.out.println(bankAccountResponse);
        assertEquals(1, bankAccountResponse.getCodigoRespuesta());
    }

    @Test
    public void testGetBankAccountsExist() {
        List<BankAccount> bankAccount = new ArrayList<>();
        bankAccount.add(new BankAccount());
        Mockito.when(service.listAll()).thenReturn(bankAccount);
        Mockito.when(bankAccountRepository.listAll()).thenReturn(bankAccount);
        BankAccountResponse bankAccountResponse = bankAccountResource.getBankAccounts();
        System.out.println(bankAccountResponse);
        assertEquals(0, bankAccountResponse.getCodigoRespuesta());
    }

    @Test
    public void testGetBankAccountsNull() {
        Mockito.when(service.listAll()).thenReturn(null);
        Mockito.when(bankAccountRepository.listAll()).thenReturn(null);
        BankAccountResponse bankAccountResponse = bankAccountResource.getBankAccounts();
        System.out.println(bankAccountResponse);
        assertEquals(2, bankAccountResponse.getCodigoRespuesta());
    }

    @Test
    public void testFindABankAccountExist() {
        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount bankAccount = new BankAccount(Long.valueOf("1"), "123456789", new Date(), "description", Double.parseDouble("1200"), "0");
        bankAccounts.add(bankAccount);
        Mockito.when(service.findById(bankAccounts.get(0).getIdBankAccount())).thenReturn(bankAccount);
        Mockito.when(bankAccountRepository.findById(bankAccounts.get(0).getIdBankAccount())).thenReturn(bankAccount);
        assertEquals("123456789", bankAccounts.get(0).getNumberAccount());
    }

    @Test
    public void testFindABankAccountNull() {
        List<BankAccount> bankAccounts = null;
        Mockito.when(service.findById(Long.valueOf(1))).thenReturn(null);
        Mockito.when(bankAccountRepository.findById(Long.valueOf(1))).thenReturn(null);
        assertEquals(null, (Object) null);
    }

    @Test
    @Transactional
    public void testDeleteBankAccountNoExist() {
        List<BankAccount> clients = new ArrayList<>();
        BankAccount bankAccount = new BankAccount(Long.valueOf("1"), "123456789", new Date(), "description", Double.parseDouble("1200"), "0");
        clients.add(bankAccount);
        Mockito.when(service.findById(clients.get(0).getIdBankAccount())).thenReturn(bankAccount);
        Mockito.when(bankAccountRepository.findById(clients.get(0).getIdBankAccount())).thenReturn(bankAccount);
        service.delete(clients.get(0).getIdBankAccount());
        ResponseBase response = bankAccountResource.getBankAccounts();
        assertEquals(1, response.getCodigoRespuesta());
    }

}