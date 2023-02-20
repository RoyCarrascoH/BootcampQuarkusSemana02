package nttdata.bootcamp.quarkus.audit.application;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import nttdata.bootcamp.quarkus.audit.entity.Audit;

public interface AuditService {

    public Multi<Audit> streamAllPosts();

    public Uni<Audit> updateAudit(String idAudit, Audit audit);

    public Uni<Void> deleteAudit(String idAudit);

}
