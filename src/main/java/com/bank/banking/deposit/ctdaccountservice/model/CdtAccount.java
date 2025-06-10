package com.bank.banking.deposit.ctdaccountservice.model;

import lombok.Getter;

import java.util.UUID;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

import com.bank.banking.deposit.ctdaccountservice.model.CdtAccount.CdtAccountIdentifier;
import com.bank.banking.deposit.ctdaccountservice.model.CdtProduct;

@Getter
public class CdtAccount implements AggregateRoot<CdtAccount, CdtAccountIdentifier> {
    private final CdtAccountIdentifier id;
    private String alias;

    public CdtAccount(String alias) {
        this.id = new CdtAccountIdentifier(UUID.randomUUID());
        this.alias = alias;
    }
    
    public record CdtAccountIdentifier(UUID id) implements Identifier {}    

    @Override
    public CdtAccount.CdtAccountIdentifier getId() {
        return id;
    }
}
