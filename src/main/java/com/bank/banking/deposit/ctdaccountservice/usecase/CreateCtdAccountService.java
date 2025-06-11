package com.bank.banking.deposit.ctdaccountservice.usecase;

import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccount;
import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccountRepository;
import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccountStatus;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateCtdAccountService implements CreateCtdAccountUseCase {
    private final CtdAccountRepository repository;
    private final Clock clock;

    public CreateCtdAccountService(CtdAccountRepository repository, Clock clock) {
        this.repository = repository;
        this.clock = clock;
    }

    @Override
    public CtdAccount createAccount(CreateCtdAccountCommand command) {
        if (repository.existsByAlias(command.alias())) {
            throw new IllegalArgumentException("Alias must be unique");
        }
        CtdAccount account = new CtdAccount(
            UUID.randomUUID(),
            command.alias(),
            CtdAccountStatus.PENDING_CREATION,
            LocalDateTime.now(clock),
            LocalDateTime.now(clock)
        );
        return repository.save(account);
    }
}
