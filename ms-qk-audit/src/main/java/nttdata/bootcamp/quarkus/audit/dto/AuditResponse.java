package nttdata.bootcamp.quarkus.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nttdata.bootcamp.quarkus.audit.entity.Audit;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditResponse extends ResponseBase {

    private List<Audit> audits;

}
