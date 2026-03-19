package com.apple.privacycenter.portal.service;



import com.apple.privacycenter.portal.entity.RequestAuditEntry;
import com.apple.privacycenter.portal.enums.ProcessingStatus;
import com.apple.privacycenter.portal.payload.CreateRequestPayload;
import com.apple.privacycenter.portal.payload.RequestView;

import java.util.List;

public interface PrivacyRequestService {
    RequestView submitRequest(CreateRequestPayload payload, String username);
    List<RequestView> fetchRequestsForUser(String username);
    List<RequestView> fetchAllRequests();
    RequestView reviseStatus(Long requestId, ProcessingStatus newStatus, String updatedBy);
    List<RequestAuditEntry> fetchAuditEntries();
}