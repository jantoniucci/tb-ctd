package com.bank.banking.deposit.ctdaccountservice.infrastructure.persistence.adapter;

import com.bank.banking.deposit.ctdaccountservice.infrastructure.persistence.entity.CtdAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataCtdAccountRepository extends JpaRepository<CtdAccountEntity, UUID> {
    Optional<CtdAccountEntity> findByAlias(String alias);
    boolean existsByAlias(String alias);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CtdAccountEntity> findWithLockingById(UUID id);
}
