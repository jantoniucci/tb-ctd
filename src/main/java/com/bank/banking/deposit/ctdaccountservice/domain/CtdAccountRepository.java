package com.bank.banking.deposit.ctdaccountservice.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CtdAccountRepository {
    CtdAccount save(CtdAccount account);
    Optional<CtdAccount> findById(UUID id);
    Optional<CtdAccount> findByAlias(String alias);
    List<CtdAccount> findAll();
    void deleteById(UUID id);
    boolean existsByAlias(String alias);
}
