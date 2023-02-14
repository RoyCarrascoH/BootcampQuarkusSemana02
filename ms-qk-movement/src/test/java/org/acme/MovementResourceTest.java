package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import nttdata.bootcamp.quarkus.movement.MovementResource;
import nttdata.bootcamp.quarkus.movement.dto.MovementResponse;
import nttdata.bootcamp.quarkus.movement.entity.MovementEntity;
import nttdata.bootcamp.quarkus.movement.repository.MovementRepository;
import nttdata.bootcamp.quarkus.movement.service.MovementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class MovementResourceTest {
    @Inject
    MovementResource movementResource;
    @InjectMock
    MovementService service;
    @InjectMock
    MovementRepository movementRepository;

    @Test
    public void testGetMovementNoExist() {
        Mockito.when(service.listAll()).thenReturn(new ArrayList<>());
        Mockito.when(movementRepository.listAll()).thenReturn(new ArrayList<>());
        MovementResponse movementResponse = movementResource.getMovements();
        System.out.println(movementResponse);
        assertEquals(1, movementResponse.getCodigoRespuesta());
    }

    @Test
    public void testGetMovementsExist() {
        List<MovementEntity> movement = new ArrayList<>();
        movement.add(new MovementEntity());
        Mockito.when(service.listAll()).thenReturn(movement);
        Mockito.when(movementRepository.listAll()).thenReturn(movement);
        MovementResponse movementResponse = movementResource.getMovements();
        System.out.println(movementResponse);
        assertEquals(0, movementResponse.getCodigoRespuesta());
    }
    @Test
    public void testGetMovementsNull() {
        Mockito.when(service.listAll()).thenReturn(null);
        Mockito.when(movementRepository.listAll()).thenReturn(null);
        MovementResponse movementResponse = movementResource.getMovements();
        System.out.println(movementResponse);
        assertEquals(2, movementResponse.getCodigoRespuesta());
    }
}