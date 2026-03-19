package com.apple.privacycenter.portal.payload;

import com.apple.privacycenter.portal.enums.ProcessingStatus;
import jakarta.validation.constraints.NotNull;

public class StatusUpdatePayload {

    @NotNull
    private ProcessingStatus processingStatus;

    public ProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(ProcessingStatus processingStatus) {
        this.processingStatus = processingStatus;
    }
}