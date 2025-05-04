package com.example.management.controller;

import com.example.management.dto.request.AccountCreateReq;
import com.example.management.dto.request.AccountFilterReq;
import com.example.management.dto.request.AccountUpdateReq;
import com.example.management.dto.response.AccountRes;
import com.example.management.entity.Accounts;
import com.example.management.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@Validated
public class AccountController {

    private final IAccountService accountService;

    @GetMapping("/res")
    public ResponseEntity<List<AccountRes>> getAllAccountRes() {
        List<AccountRes> accounts = accountService.getAllAccountRes();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accounts> updateAccount(@PathVariable Long id, @RequestBody @Valid AccountUpdateReq accountUpdateReq) {
        try {
            Accounts updateAccount = accountService.updateAccount(id, accountUpdateReq);
            return ResponseEntity.ok(updateAccount);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<Accounts> createAccount(@RequestBody @Valid AccountCreateReq accountCreateReq) {
        Accounts response = accountService.addAccount(accountCreateReq);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AccountRes>> filterAccounts(AccountFilterReq filterReq) {
        List<AccountRes> filteredAccounts = accountService.filter(filterReq);
        return ResponseEntity.ok(filteredAccounts);
    }

    @GetMapping()
    public ResponseEntity<Page<Accounts>> getAllAccount(
            @RequestParam int page,
            @RequestParam int size) {
        Page<Accounts> accounts = accountService.getAllAccount(page, size);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("{id}")
    public ResponseEntity<Accounts> getAccountById(@PathVariable Long id) {
        Accounts account = accountService.getAccountById(id);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


