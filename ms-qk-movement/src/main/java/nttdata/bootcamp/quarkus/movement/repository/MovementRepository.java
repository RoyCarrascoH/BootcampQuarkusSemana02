package nttdata.bootcamp.quarkus.movement.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import nttdata.bootcamp.quarkus.movement.entity.MovementEntity;

@ApplicationScoped
public class MovementRepository implements PanacheRepository<MovementEntity> {
}