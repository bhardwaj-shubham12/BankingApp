package com.projects.bankingApp.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @GetMapping("/{Id}")
    public Account getAccount(@PathVariable Long Id){
        return accountService.getAccount(Id).orElseThrow(() -> new RuntimeException("Account not Found"));
    }

    @PostMapping("/{Id}/deposit")
    public Account deposit(@PathVariable Long Id, @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        return accountService.deposit(Id,amount);
    }

    @PostMapping("/{Id}/withdraw")
    public Account withdraw(@PathVariable Long Id, @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        return accountService.withdraw(Id,amount);
    }

    @PutMapping("/{Id1}/{Id2}/transfer")
    public Account transfer(@PathVariable Long Id1, @PathVariable Long Id2, @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        accountService.withdraw(Id1,amount);
        return accountService.deposit(Id2,amount);
    }
}
