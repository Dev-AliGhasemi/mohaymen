package ir.mohaymen.tsm.core.application_services.audit_log.services;

import ir.mohaymen.tsm.core.domain_models.audit_log.entities.AuditLog;
import ir.mohaymen.tsm.core.domain_models.audit_log.entities.OperationType;
import ir.mohaymen.tsm.core.domain_services.audit_log.repositories.AuditRepository;
import ir.mohaymen.tsm.framework.entities.BaseEntity;
import ir.mohaymen.tsm.framework.events.Event;
import jakarta.persistence.*;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Field;
import java.util.List;

public class AuditLogListener {

    private final AuditRepository auditRepository;

    public AuditLogListener(@Lazy AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @PostPersist
    public void persist(BaseEntity entity) {
        saveAuditLog(OperationType.CREATE, entity);
    }

    @PostRemove
    public void remove(BaseEntity entity) {
        saveAuditLog(OperationType.DELETE, entity);
    }

    @PostUpdate
    public void update(BaseEntity entity) {
        saveAuditLog(OperationType.UPDATE, entity);
    }

    @SneakyThrows
    private void saveAuditLog(OperationType operationType, BaseEntity entity) {
        Class<? extends BaseEntity> clazz = entity.getClass();
        Field eventsField = clazz.getSuperclass().getDeclaredField("events");
        Field idField = clazz.getSuperclass().getDeclaredField("id");
        eventsField.setAccessible(true);
        idField.setAccessible(true);
        List<Event> events = (List<Event>) eventsField.get(entity);
        Long id = (Long) idField.get(entity);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AuditLog auditLog = new AuditLog(clazz.getTypeName(), id, operationType,events.toString(), username);
        auditRepository.save(auditLog);
    }
}
