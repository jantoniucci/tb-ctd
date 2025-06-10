package com.bank.banking.deposit.ctdaccountservice.model;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.lang.Nullable;

import com.bank.banking.deposit.ctdaccountservice.model.CdtAccount;
import com.bank.banking.deposit.ctdaccountservice.model.CdtAccount.CdtAccountIdentifier;

public interface CdtAccounts extends CrudRepository<CdtAccount, CdtAccountIdentifier> {

	@Nullable
	CdtAccount findByAlias(String alias);

	Streamable<CdtAccount> findAll(Sort sort);

	Streamable<CdtAccount> findByAliasContaining(String alias, Sort sort);
}
