package nttdata.bootcamp.quarkus.loan.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.loan.entity.LoanEntity;

@ApplicationScoped
public class LoanRepository implements PanacheRepository<LoanEntity> {
}
