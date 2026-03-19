package com.apple.privacycenter.portal.mapper;


import com.apple.privacycenter.portal.enums.ProcessingStatus;
import com.apple.privacycenter.portal.entity.PrivacyRequest;
import com.apple.privacycenter.portal.payload.CreateRequestPayload;
import com.apple.privacycenter.portal.payload.RequestView;

import java.time.LocalDateTime;

public final class PrivacyRequestMapper {

    private PrivacyRequestMapper() {
    }

    public static PrivacyRequest fromPayload(CreateRequestPayload payload, String username) {
        LocalDateTime now = LocalDateTime.now();

        return new PrivacyRequest(
                payload.getCustomerName(),
                payload.getEmailAddress(),
                payload.getRequestCategory(),
                payload.getNotes(),
                ProcessingStatus.PENDING,
                username,
                now,
                now
        );
    }

    public static RequestView toView(PrivacyRequest entity) {
        return new RequestView(
                entity.getId(),
                entity.getCustomerName(),
                entity.getEmailAddress(),
                entity.getRequestCategory(),
                entity.getNotes(),
                entity.getProcessingStatus(),
                entity.getOwnerUsername(),
                entity.getCreatedOn(),
                entity.getModifiedOn()
        );
    }
}