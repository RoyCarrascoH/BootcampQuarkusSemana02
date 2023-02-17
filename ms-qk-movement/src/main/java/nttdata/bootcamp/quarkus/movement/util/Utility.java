package nttdata.bootcamp.quarkus.movement.util;


import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.movement.dto.*;
import nttdata.bootcamp.quarkus.movement.entity.BankAccount;
import nttdata.bootcamp.quarkus.movement.entity.CreditCardEntity;
import nttdata.bootcamp.quarkus.movement.entity.LoanEntity;
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

    public static MovementsByAccountNumber uploadMovementsBankAccount(List<MovementEntity> entity, MovementsByAccountNumber response) {

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

    public static MovementsByCreditCardNumber uploadCreditCard(CreditCardEntity entity, MovementsByCreditCardNumber response) {

        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(entity.getCreditCardNumber());
        creditCard.setCreditLimit(entity.getCreditLimit());
        creditCard.setBalanceAvailable(entity.getBalanceAvailable());
        creditCard.setUsedCredit(entity.getCreditLimit() - entity.getBalanceAvailable());
        creditCard.setClosingDate(entity.getClosingDate());
        response.setCreditCard(creditCard);
        return response;
    }

    public static MovementsByCreditCardNumber uploadMovementsCreditCard(List<MovementEntity> entity, MovementsByCreditCardNumber response) {

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

    public static MovementsByLoanNumber uploadLoan(LoanEntity entity, MovementsByLoanNumber response) {

        Loan loan = new Loan();
        loan.setLoanNumber(entity.getLoanNumber());
        loan.setAmountPaid(entity.getInitialBalance() - entity.getCurrentBalance());
        loan.setCurrentBalance(entity.getCurrentBalance());
        loan.setInitialBalance(entity.getInitialBalance());
        loan.setNumberQuotaPaid(entity.getAmountOfFeesPaid());
        loan.setNumberQuotaPendient(entity.getQuotaNumber() - entity.getAmountOfFeesPaid());
        response.setLoan(loan);
        return response;
    }

    public static MovementsByLoanNumber uploadMovementsLoan(List<MovementEntity> entity, MovementsByLoanNumber response) {

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