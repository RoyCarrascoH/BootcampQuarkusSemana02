package nttdata.bootcamp.quarkus.loan.service;

import nttdata.bootcamp.quarkus.loan.entity.LoanEntity;

import java.util.List;

public interface LoanService {

    public List<LoanEntity> listAll();

    public LoanEntity findById(Long id);

    public void save(LoanEntity client);

    public LoanEntity update(Long id, LoanEntity client);

    public void delete(Long id);

    public LoanEntity findByLoanNumber(String loanNumber);

    public List<LoanEntity> findLoanByDocumentNumber(String documentNumber);
}