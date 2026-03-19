package com.apple.privacycenter.portal.payload;


import com.apple.privacycenter.portal.enums.PrivacyRequestType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateRequestPayload {

    @NotBlank
    private String customerName;

    @NotBlank
    @Email
    private String emailAddress;

    @NotNull
    private PrivacyRequestType requestCategory;

    @NotBlank
    private String notes;

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
}