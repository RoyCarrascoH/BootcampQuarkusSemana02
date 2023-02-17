package nttdata.bootcamp.quarkus.audit.application;

import nttdata.bootcamp.quarkus.audit.entity.Audit;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditServiceImpl implements AuditService {

    @Override
    public Multi<Audit> streamAllPosts() {
        return Audit.streamAll();
    }

	@Override
	public Uni<Audit> saveAudit(Audit audit) {
		return null;
	}

	@Override
	public Uni<Audit> updateAudit(Audit audit) {
		return null;
	}

	@Override
	public Uni<Audit> deleteAudit(Audit audit) {
		return null;
	}

}
