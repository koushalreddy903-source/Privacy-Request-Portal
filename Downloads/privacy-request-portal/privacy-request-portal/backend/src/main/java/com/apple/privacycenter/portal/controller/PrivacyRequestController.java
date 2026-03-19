package com.apple.privacycenter.portal.controller;


import com.apple.privacycenter.portal.entity.RequestAuditEntry;
import com.apple.privacycenter.portal.payload.CreateRequestPayload;
import com.apple.privacycenter.portal.payload.RequestView;
import com.apple.privacycenter.portal.payload.StatusUpdatePayload;
import com.apple.privacycenter.portal.service.PrivacyRequestService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/privacy-requests")
@CrossOrigin(origins = "http://localhost:4200")
public class PrivacyRequestController {

    private final PrivacyRequestService privacyRequestService;

    public PrivacyRequestController(PrivacyRequestService privacyRequestService) {
        this.privacyRequestService = privacyRequestService;
    }

    @PostMapping
    public RequestView submitRequest(
            @Valid @RequestBody CreateRequestPayload payload,
            Authentication authentication
    ) {
        return privacyRequestService.submitRequest(payload, authentication.getName());
    }

    @GetMapping("/mine")
    public List<RequestView> fetchCurrentUserRequests(Authentication authentication) {
        return privacyRequestService.fetchRequestsForUser(authentication.getName());
    }

    @GetMapping("/all")
    public List<RequestView> fetchAllRequests() {
        return privacyRequestService.fetchAllRequests();
    }

    @PatchMapping("/{requestId}/status")
    public RequestView changeRequestStatus(
            @PathVariable Long requestId,
            @Valid @RequestBody StatusUpdatePayload payload,
            Authentication authentication
    ) {
        return privacyRequestService.reviseStatus(
                requestId,
                payload.getProcessingStatus(),
                authentication.getName()
        );
    }

    @GetMapping("/audit")
    public List<RequestAuditEntry> fetchAuditTrail() {
        return privacyRequestService.fetchAuditEntries();
    }
}