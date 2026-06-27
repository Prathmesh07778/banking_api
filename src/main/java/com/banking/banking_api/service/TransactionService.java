package com.banking.banking_api.service;

import com.banking.banking_api.entity.Account;
import com.banking.banking_api.entity.Transaction;
import com.banking.banking_api.repository.AccountRepository;
import com.banking.banking_api.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Account deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setToAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");
        transactionRepository.save(transaction);

        return account;
    }

    public Account withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow();

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setFromAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setType("WITHDRAW");
        transactionRepository.save(transaction);

        return account;
    }

    public String transfer(Long fromId, Long toId, double amount) {
        Account from = accountRepository.findById(fromId).orElseThrow();
        Account to = accountRepository.findById(toId).orElseThrow();

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        accountRepository.save(from);
        accountRepository.save(to);

        Transaction transaction = new Transaction();
        transaction.setFromAccountId(fromId);
        transaction.setToAccountId(toId);
        transaction.setAmount(amount);
        transaction.setType("TRANSFER");
        transactionRepository.save(transaction);

        return "Transfer successful";
    }

    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionRepository
                .findByFromAccountIdOrToAccountId(accountId, accountId);
    }
}