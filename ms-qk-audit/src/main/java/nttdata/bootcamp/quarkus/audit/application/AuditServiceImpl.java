package nttdata.bootcamp.quarkus.audit.application;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import nttdata.bootcamp.quarkus.audit.entity.Audit;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class AuditServiceImpl implements AuditService {

    @Override
    public Multi<Audit> streamAllPosts() {
        return Audit.streamAll();
    }

    @Override
    public Uni<Audit> updateAudit(String idAudit, Audit audit) {
        Uni<Audit> auditUni = Audit.findById(new ObjectId(idAudit));
        return auditUni
                .onItem().transform(au -> {
                    au.setIdAudit(new ObjectId(idAudit));
                    au.setIdTransaccion(audit.getIdTransaccion());
                    au.setUser(audit.getUser());
                    au.setRequest(audit.getRequest());
                    au.setResponse(audit.getResponse());
                    au.setCodigoRespuesta(audit.getCodigoRespuesta());
                    au.setMensajeRespuesta(audit.getMensajeRespuesta());
                    return au;
                }).call(au -> au.persistOrUpdate());
    }

    @Override
    public Uni<Void> deleteAudit(String idAudit) {
        Uni<Audit> auditUni = Audit.findById(new ObjectId(idAudit));
        return auditUni.call(ReactivePanacheMongoEntityBase::delete).replaceWithVoid();
    }

}
