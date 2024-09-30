package com.motaData.kafka.audit.serviceImpl;

import com.motaData.kafka.audit.AuditLog;
import com.motaData.kafka.audit.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService {
    private final AuditLogRepository auditRepo;

    public AuditLogService(AuditLogRepository auditRepo) {
        this.auditRepo = auditRepo;
    }

    public void saveAuditLog(AuditLog auditLog) {
        auditRepo.save(auditLog);
    }
}
