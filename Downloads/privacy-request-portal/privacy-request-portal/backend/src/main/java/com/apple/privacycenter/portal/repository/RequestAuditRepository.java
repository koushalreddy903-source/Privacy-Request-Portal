package com.apple.privacycenter.portal.repository;


import com.apple.privacycenter.portal.entity.RequestAuditEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestAuditRepository extends JpaRepository<RequestAuditEntry, Long> {
    List<RequestAuditEntry> findByLinkedRequestIdOrderByEventTimeDesc(Long linkedRequestId);
}