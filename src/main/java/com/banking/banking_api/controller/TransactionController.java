package com.banking.banking_api.controller;

import com.banking.banking_api.entity.Account;
import com.banking.banking_api.entity.Transaction;
import com.banking.banking_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit/{accountId}")
    public Account deposit(@PathVariable Long accountId,
                           @RequestParam double amount) {
        return transactionService.deposit(accountId, amount);
    }

    @PostMapping("/withdraw/{accountId}")
    public Account withdraw(@PathVariable Long accountId,
                            @RequestParam double amount) {
        return transactionService.withdraw(accountId, amount);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromId,
                           @RequestParam Long toId,
                           @RequestParam double amount) {
        return transactionService.transfer(fromId, toId, amount);
    }

    @GetMapping("/history/{accountId}")
    public List<Transaction> getHistory(@PathVariable Long accountId) {
        return transactionService.getTransactionHistory(accountId);
    }
}