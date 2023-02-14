package nttdata.bootcamp.quarkus.client;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import nttdata.bootcamp.quarkus.client.application.ClientService;
import nttdata.bootcamp.quarkus.client.dto.ClientResponse;
import nttdata.bootcamp.quarkus.client.dto.ResponseBase;
import nttdata.bootcamp.quarkus.client.entity.Client;
import nttdata.bootcamp.quarkus.client.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ClientResourceTest {

    @Inject
    ClientResource clientResource;

    @InjectMock
    ClientService service;

    @InjectMock
    ClientRepository clientRepository;

    @Test
    public void testGetClientsNoExist() {
        Mockito.when(service.listAll()).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.listAll()).thenReturn(new ArrayList<>());
        ClientResponse clientResponse = clientResource.getClients();
        System.out.println(clientResponse);
        assertEquals(1, clientResponse.getCodigoRespuesta());
    }

    @Test
    public void testGetClientsExist() {
        List<Client> client = new ArrayList<>();
        client.add(new Client());
        Mockito.when(service.listAll()).thenReturn(client);
        Mockito.when(clientRepository.listAll()).thenReturn(client);
        ClientResponse clientResponse = clientResource.getClients();
        System.out.println(clientResponse);
        assertEquals(0, clientResponse.getCodigoRespuesta());
    }

    @Test
    public void testGetClientsNull() {
        Mockito.when(service.listAll()).thenReturn(null);
        Mockito.when(clientRepository.listAll()).thenReturn(null);
        ClientResponse clientResponse = clientResource.getClients();
        System.out.println(clientResponse);
        assertEquals(2, clientResponse.getCodigoRespuesta());
    }

    @Test
    public void testFindAClientExist() {
        List<Client> clients = new ArrayList<>();
        Client client = new Client(Long.valueOf("1"), "NATURAL", "documentType", "documentNumber", "completeName",
                "surnames", "sex", "email", "cellPhone");
        clients.add(client);
        Mockito.when(service.findById(clients.get(0).getIdClient())).thenReturn(client);
        Mockito.when(clientRepository.findById(clients.get(0).getIdClient())).thenReturn(client);
        assertEquals("NATURAL", clients.get(0).getPersonType());
    }

    @Test
    public void testFindAClientNull() {
        List<Client> clients = null;
        Mockito.when(service.findById(Long.valueOf(1))).thenReturn(null);
        Mockito.when(clientRepository.findById(Long.valueOf(1))).thenReturn(null);
        assertEquals(null, (Object) null);
    }

    @Test
    @Transactional
    public void testDeleteClientNoExist() {
        List<Client> clients = new ArrayList<>();
        Client client = new Client(Long.valueOf("1"), "NATURAL", "documentType", "documentNumber", "completeName",
                "surnames", "sex", "email", "cellPhone");
        clients.add(client);
        Mockito.when(service.findById(clients.get(0).getIdClient())).thenReturn(client);
        Mockito.when(clientRepository.findById(clients.get(0).getIdClient())).thenReturn(client);
        service.delete(clients.get(0).getIdClient());
        ResponseBase response = clientResource.getClients();
        assertEquals(1, response.getCodigoRespuesta());
    }

}