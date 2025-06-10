package com.bank.banking.deposit.ctdaccountservice.model;

import com.bank.banking.core.model.BankingProduct;
import lombok.Getter;

import java.util.UUID;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

public class CdtProduct extends BankingProduct {

    public CdtProduct() {
        super(new BankingProduct.BankingProductIdentifier(UUID.fromString("1")), "CDT");
    }

}
