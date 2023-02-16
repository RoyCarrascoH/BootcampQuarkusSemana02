package nttdata.bootcamp.quarkus.movement.util;


import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.movement.dto.Movement;
import nttdata.bootcamp.quarkus.movement.dto.MovementsByAccountNumber;
import nttdata.bootcamp.quarkus.movement.entity.BankAccount;
import nttdata.bootcamp.quarkus.movement.entity.MovementEntity;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Utility {

    public static MovementsByAccountNumber uploadBankAccount(BankAccount entity, MovementsByAccountNumber response) {

        nttdata.bootcamp.quarkus.movement.dto.BankAccount bankAccount = new nttdata.bootcamp.quarkus.movement.dto.BankAccount();
        bankAccount.setBankAccountNumber(entity.getNumberAccount());
        bankAccount.setAmount(entity.getAmount());
        response.setBankAccount(bankAccount);
        return response;
    }

    public static MovementsByAccountNumber uploadMovements(List<MovementEntity> entity, MovementsByAccountNumber response) {

        List<Movement> movements = new ArrayList<>();
        for (MovementEntity valor : entity) {
            Movement movement = new Movement();
            movement.setMovementDate(valor.getDateMovement());
            movement.setMovementDescription(valor.getDescriptionMovement());
            movement.setAmount(valor.getTotalMovement());
            movements.add(movement);
        }
        response.setMovements(movements);
        return response;
    }

}