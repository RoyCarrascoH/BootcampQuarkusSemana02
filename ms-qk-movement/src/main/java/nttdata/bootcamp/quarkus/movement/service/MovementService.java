package nttdata.bootcamp.quarkus.movement.service;

import nttdata.bootcamp.quarkus.movement.dto.MovementsByAccountNumber;
import nttdata.bootcamp.quarkus.movement.entity.BankAccount;
import nttdata.bootcamp.quarkus.movement.entity.MovementEntity;

import java.util.List;

public interface MovementService {
    public List<MovementEntity> listAll();

    public MovementEntity findById(Long id);

    public void save(MovementEntity movement);

    public MovementEntity update(Long id, MovementEntity movement);

    public List<MovementEntity> findMovementsByAccountNumber(String bankAccountNumber);

    public List<MovementEntity> findMovementsByCreditCardNumber(String creditCardNumber);

    public List<MovementEntity> findMovementsByLoanNumber(String loanNumber);

}