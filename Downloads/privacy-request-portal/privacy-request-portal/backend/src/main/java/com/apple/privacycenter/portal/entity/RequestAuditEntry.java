package com.apple.privacycenter.portal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_audit_entries")
public class RequestAuditEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;

    private String actor;

    private Long linkedRequestId;

    private LocalDateTime eventTime;

    @Column(length = 1000)
    private String detailMessage;

    public RequestAuditEntry() {
    }

    public RequestAuditEntry(String eventName, String actor, Long linkedRequestId, LocalDateTime eventTime, String detailMessage) {
        this.eventName = eventName;
        this.actor = actor;
        this.linkedRequestId = linkedRequestId;
        this.eventTime = eventTime;
        this.detailMessage = detailMessage;
    }

    public Long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getActor() {
        return actor;
    }

    public Long getLinkedRequestId() {
        return linkedRequestId;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setLinkedRequestId(Long linkedRequestId) {
        this.linkedRequestId = linkedRequestId;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}