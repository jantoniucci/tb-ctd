package com.bank.banking.deposit.ctdaccountservice.infrastructure.persistence.adapter;

import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccount;
import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccountRepository;
import com.bank.banking.deposit.ctdaccountservice.infrastructure.persistence.entity.CtdAccountEntity;
import com.bank.banking.deposit.ctdaccountservice.infrastructure.persistence.mapper.CtdAccountMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CtdAccountRepositoryAdapter implements CtdAccountRepository {
    private final SpringDataCtdAccountRepository jpaRepository;
    private final CtdAccountMapper mapper;

    public CtdAccountRepositoryAdapter(SpringDataCtdAccountRepository jpaRepository, CtdAccountMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CtdAccount save(CtdAccount account) {
        CtdAccountEntity entity = mapper.toEntity(account);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<CtdAccount> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<CtdAccount> findByAlias(String alias) {
        return jpaRepository.findByAlias(alias).map(mapper::toDomain);
    }

    @Override
    public List<CtdAccount> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return jpaRepository.existsByAlias(alias);
    }
}
