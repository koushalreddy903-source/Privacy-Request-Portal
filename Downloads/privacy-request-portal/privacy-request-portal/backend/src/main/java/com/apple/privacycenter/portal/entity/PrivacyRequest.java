package com.apple.privacycenter.portal.entity;

import com.apple.privacycenter.portal.enums.PrivacyRequestType;
import com.apple.privacycenter.portal.enums.ProcessingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "privacy_requests")
public class PrivacyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String emailAddress;

    @Enumerated(EnumType.STRING)
    private PrivacyRequestType requestCategory;

    @Column(length = 1000)
    private String notes;

    @Enumerated(EnumType.STRING)
    private ProcessingStatus processingStatus;

    private String ownerUsername;

    private LocalDateTime createdOn;

    private LocalDateTime modifiedOn;

    public PrivacyRequest() {
    }

    public PrivacyRequest(
            String customerName,
            String emailAddress,
            PrivacyRequestType requestCategory,
            String notes,
            ProcessingStatus processingStatus,
            String ownerUsername,
            LocalDateTime createdOn,
            LocalDateTime modifiedOn
    ) {
        this.customerName = customerName;
        this.emailAddress = emailAddress;
        this.requestCategory = requestCategory;
        this.notes = notes;
        this.processingStatus = processingStatus;
        this.ownerUsername = ownerUsername;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public void changeStatus(ProcessingStatus status) {
        this.processingStatus = status;
        this.modifiedOn = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public PrivacyRequestType getRequestCategory() {
        return requestCategory;
    }

    public String getNotes() {
        return notes;
    }

    public ProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setRequestCategory(PrivacyRequestType requestCategory) {
        this.requestCategory = requestCategory;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setProcessingStatus(ProcessingStatus processingStatus) {
        this.processingStatus = processingStatus;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}