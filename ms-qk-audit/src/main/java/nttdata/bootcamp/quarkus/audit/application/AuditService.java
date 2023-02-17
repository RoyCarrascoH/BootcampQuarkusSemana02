package nttdata.bootcamp.quarkus.audit.application;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import nttdata.bootcamp.quarkus.audit.entity.Audit;

public interface AuditService {

    public Multi<Audit> streamAllPosts();

    public Uni<Audit> saveAudit(Audit audit);

    public Uni<Audit> updateAudit(Audit audit);

    public Uni<Audit> deleteAudit(Audit audit);

}
