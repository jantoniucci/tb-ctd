package com.bank.banking.deposit.ctdaccountservice.infrastructure.api;

import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccount;
import com.bank.banking.deposit.ctdaccountservice.domain.CtdAccountRepository;
import com.bank.banking.deposit.ctdaccountservice.usecase.CreateCtdAccountCommand;
import com.bank.banking.deposit.ctdaccountservice.usecase.CreateCtdAccountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ctd-accounts")
public class CtdAccountController {
    private final CreateCtdAccountUseCase createCtdAccountUseCase;
    private final CtdAccountRepository ctdAccountRepository;

    @Autowired
    public CtdAccountController(CreateCtdAccountUseCase createCtdAccountUseCase, CtdAccountRepository ctdAccountRepository) {
        this.createCtdAccountUseCase = createCtdAccountUseCase;
        this.ctdAccountRepository = ctdAccountRepository;
    }

    @PostMapping
    public ResponseEntity<CtdAccount> create(@RequestBody CreateCtdAccountRequest request) {
        CtdAccount account = createCtdAccountUseCase.createAccount(new CreateCtdAccountCommand(request.alias()));
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CtdAccount> getById(@PathVariable UUID id) {
        Optional<CtdAccount> account = ctdAccountRepository.findById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/alias/{alias}")
    public ResponseEntity<CtdAccount> getByAlias(@PathVariable String alias) {
        Optional<CtdAccount> account = ctdAccountRepository.findByAlias(alias);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CtdAccount> listAll() {
        return ctdAccountRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        if (ctdAccountRepository.findById(id).isPresent()) {
            ctdAccountRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/alias")
    public ResponseEntity<CtdAccount> updateAlias(@PathVariable UUID id, @RequestBody UpdateAliasRequest request) {
        Optional<CtdAccount> accountOpt = ctdAccountRepository.findById(id);
        if (accountOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CtdAccount account = accountOpt.get();
        account.setAlias(request.alias());
        account.setUpdatedAt(java.time.LocalDateTime.now());
        return ResponseEntity.ok(ctdAccountRepository.save(account));
    }

    public static record CreateCtdAccountRequest(String alias) {}
    public static record UpdateAliasRequest(String alias) {}
}
