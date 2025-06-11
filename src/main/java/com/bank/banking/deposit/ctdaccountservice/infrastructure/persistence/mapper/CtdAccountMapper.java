package com.bank.banking.deposit.ctdaccountservice.infrastructure.persistence.mapper;

import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccount;
import com.bank.banking.deposit.ctdaccountservice.infrastructure.persistence.entity.CtdAccountEntity;
import org.springframework.stereotype.Component;

@Component
public class CtdAccountMapper {
    public CtdAccountEntity toEntity(CtdAccount domain) {
        CtdAccountEntity entity = new CtdAccountEntity();
        entity.setId(domain.getId());
        entity.setAlias(domain.getAlias());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }
    public CtdAccount toDomain(CtdAccountEntity entity) {
        return new CtdAccount(
            entity.getId(),
            entity.getAlias(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
