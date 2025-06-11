package com.bank.banking.deposit.ctdaccountservice.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class CtdAccount {
    private UUID id;
    private String alias;
    private CtdAccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CtdAccount(UUID id, String alias, CtdAccountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.alias = alias;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public String getAlias() { return alias; }
    public CtdAccountStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setAlias(String alias) { this.alias = alias; }
    public void setStatus(CtdAccountStatus status) { this.status = status; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
