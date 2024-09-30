package com.motaData.kafka.consumer;


import com.motaData.kafka.audit.AuditLog;
import com.motaData.kafka.audit.AuditLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final AuditLogRepository auditLogRepository;

    public KafkaConsumerService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @KafkaListener(topics = "audit_logs", groupId = "group_id")
    public void consume(String message) {
        // Save message to audit log
        AuditLog log = new AuditLog(message);
        auditLogRepository.save(log);
        LOGGER.info("Consumed message: " + message);
    }
}
