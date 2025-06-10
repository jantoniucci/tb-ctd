package com.bank.banking.core.model;

import java.util.UUID;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;
import com.bank.banking.deposit.ctdaccountservice.model.CdtProduct;
import com.bank.banking.core.model.BankingProduct.BankingProductIdentifier;

public class BankingProduct implements AggregateRoot<BankingProduct, BankingProductIdentifier> {

    private final BankingProductIdentifier id;
    private final String name;

    public record BankingProductIdentifier(UUID id) implements Identifier {}    


    public BankingProduct(BankingProductIdentifier id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public BankingProduct.BankingProductIdentifier getId() {
        return id;
    }
    
}
