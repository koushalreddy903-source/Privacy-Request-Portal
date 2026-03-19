package com.apple.privacycenter.portal.service.impl;

import com.apple.privacycenter.portal.entity.PrivacyRequest;
import com.apple.privacycenter.portal.entity.RequestAuditEntry;
import com.apple.privacycenter.portal.enums.ProcessingStatus;
import com.apple.privacycenter.portal.mapper.PrivacyRequestMapper;
import com.apple.privacycenter.portal.payload.CreateRequestPayload;
import com.apple.privacycenter.portal.payload.RequestView;
import com.apple.privacycenter.portal.repository.PrivacyRequestRepository;
import com.apple.privacycenter.portal.repository.RequestAuditRepository;
import com.apple.privacycenter.portal.service.PrivacyRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrivacyRequestServiceImpl implements PrivacyRequestService {

    private final PrivacyRequestRepository privacyRequestRepository;
    private final RequestAuditRepository requestAuditRepository;

    public PrivacyRequestServiceImpl(
            PrivacyRequestRepository privacyRequestRepository,
            RequestAuditRepository requestAuditRepository
    ) {
        this.privacyRequestRepository = privacyRequestRepository;
        this.requestAuditRepository = requestAuditRepository;
    }

    @Override
    public RequestView submitRequest(CreateRequestPayload payload, String username) {
        PrivacyRequest request = PrivacyRequestMapper.fromPayload(payload, username);
        PrivacyRequest saved = privacyRequestRepository.save(request);

        recordAudit(
                "REQUEST_SUBMITTED",
                username,
                saved.getId(),
                "New privacy request created for category " + saved.getRequestCategory()
        );

        return PrivacyRequestMapper.toView(saved);
    }

    @Override
    public List<RequestView> fetchRequestsForUser(String username) {
        return privacyRequestRepository.findAllByOwnerUsernameOrderByCreatedOnDesc(username)
                .stream()
                .map(PrivacyRequestMapper::toView)
                .toList();
    }

    @Override
    public List<RequestView> fetchAllRequests() {
        return privacyRequestRepository.findAll()
                .stream()
                .map(PrivacyRequestMapper::toView)
                .toList();
    }

    @Override
    public RequestView reviseStatus(Long requestId, ProcessingStatus newStatus, String updatedBy) {
        PrivacyRequest request = privacyRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Unable to find request with id " + requestId));

        request.changeStatus(newStatus);
        PrivacyRequest saved = privacyRequestRepository.save(request);

        recordAudit(
                "REQUEST_STATUS_CHANGED",
                updatedBy,
                saved.getId(),
                "Processing status changed to " + newStatus
        );

        return PrivacyRequestMapper.toView(saved);
    }

    @Override
    public List<RequestAuditEntry> fetchAuditEntries() {
        return requestAuditRepository.findAll();
    }

    private void recordAudit(String eventName, String actor, Long linkedRequestId, String detailMessage) {
        RequestAuditEntry entry = new RequestAuditEntry(
                eventName,
                actor,
                linkedRequestId,
                LocalDateTime.now(),
                detailMessage
        );

        requestAuditRepository.save(entry);
    }
}