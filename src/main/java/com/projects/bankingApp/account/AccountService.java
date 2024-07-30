package com.projects.bankingApp.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id){
        return accountRepository.findById(id);
    }

    public Account deposit(Long Id, Double amount){
        Account account = getAccount(Id).orElseThrow(()-> new RuntimeException("Account Not Found"));
        account.setBalance(account.getBalance() + amount);

        return accountRepository.save(account);
    }

    public Account withdraw(Long Id, Double amount){
        Account account = getAccount(Id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        if(account.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }
        account.setBalance(account.getBalance() - amount);

        return accountRepository.save(account);
    }

}
