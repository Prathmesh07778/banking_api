package com.banking.banking_api.service;

import com.banking.banking_api.entity.Account;
import com.banking.banking_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service

public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {

        return accountRepository.findAll();
    }

    public  Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account createAccount(Account account){

        return  accountRepository.save(account);

    }
}