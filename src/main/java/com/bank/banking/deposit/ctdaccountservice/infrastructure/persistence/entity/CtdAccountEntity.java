package com.bank.banking.deposit.ctdaccountservice.infrastructure.persistence.entity;

import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccountStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ctd_account")
public class CtdAccountEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true, nullable = false)
    private String alias;
    @Enumerated(EnumType.STRING)
    private CtdAccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public CtdAccountStatus getStatus() { return status; }
    public void setStatus(CtdAccountStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // No @Version field, as requested. Pessimistic locking is handled at the repository/service layer.
}
