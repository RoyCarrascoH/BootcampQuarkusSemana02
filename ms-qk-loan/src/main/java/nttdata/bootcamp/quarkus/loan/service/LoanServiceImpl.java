package nttdata.bootcamp.quarkus.loan.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nttdata.bootcamp.quarkus.loan.entity.LoanEntity;
import nttdata.bootcamp.quarkus.loan.repository.LoanRepository;

import java.util.List;

@ApplicationScoped
public class LoanServiceImpl implements LoanService {

    @Inject
    LoanRepository loanRepository;

    @Override
    public List<LoanEntity> listAll() {
        return loanRepository.listAll();
    }

    @Override
    public LoanEntity findById(Long idLoan) {
        return loanRepository.findById(idLoan);
    }

    @Override
    public void save(LoanEntity loan) {
        loanRepository.persist(loan);
    }

    @Override
    public LoanEntity update(Long id, LoanEntity loan) {
        loanRepository.persist(loan);
        return loan;
    }

    @Override
    public void delete(Long id) {
        loanRepository.deleteById(id);
    }

    @Override
    public LoanEntity findByLoanNumber(String loanNumber) {
        return loanRepository.find("loanNumber", loanNumber).firstResult();
    }

    @Override
    public List<LoanEntity> findLoanByDocumentNumber(String documentNumber) {
        return loanRepository.list("client.documentNumber", documentNumber);
    }
}
