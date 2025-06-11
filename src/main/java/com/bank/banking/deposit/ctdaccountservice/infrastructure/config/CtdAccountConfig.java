package com.bank.banking.deposit.ctdaccountservice.infrastructure.config;

import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccountRepository;
import com.bank.banking.deposit.ctdaccountservice.usecase.CreateCtdAccountService;
import com.bank.banking.deposit.ctdaccountservice.usecase.CreateCtdAccountUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class CtdAccountConfig {
    @Bean
    public CreateCtdAccountUseCase createCtdAccountUseCase(CtdAccountRepository repository, Clock clock) {
        return new CreateCtdAccountService(repository, clock);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
