package ir.mohaymen.tsm.core.domain_services.audit_log.repositories;

import ir.mohaymen.tsm.core.domain_models.audit_log.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditLog,Long> {
}
