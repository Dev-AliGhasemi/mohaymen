package ir.mohaymen.tsm.core.domain_models.audit_log.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "audit_log", schema = "tsm", indexes = {@Index(name = "idx_entity_id", columnList = "entity_id"),
        @Index(name = "idx_entity_name", columnList = "entity_name")})
@Entity
@NoArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "entity_name", nullable = false, updatable = false)
    private String entityName;
    @Column(name = "entity_id", nullable = false, updatable = false)
    private Long entityId;
    @Column(name = "operation_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @Column(name = "date_time", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dateTime;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Lob
    private String details;

    public AuditLog(String entityName, Long entityId, OperationType operationType, String details, String modifiedBy) {
        this.entityName = entityName;
        this.entityId = entityId;
        this.operationType = operationType;
        this.details = details;
        this.modifiedBy = modifiedBy;
    }

}
