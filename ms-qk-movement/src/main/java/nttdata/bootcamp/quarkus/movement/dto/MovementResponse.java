package nttdata.bootcamp.quarkus.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nttdata.bootcamp.quarkus.movement.entity.MovementEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponse extends ResponseBase {
    List<MovementEntity> movement;
}
