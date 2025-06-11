package com.bank.banking.deposit.ctdaccountservice.usecase;

import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccount;

public interface CreateCtdAccountUseCase {
    CtdAccount createAccount(CreateCtdAccountCommand command);
}
