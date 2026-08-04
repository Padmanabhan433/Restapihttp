package com.tns.RestApiHttpMethods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tns.RestApiHttpMethods.entity.Account;
import com.tns.RestApiHttpMethods.repository.AccountRepository;
import com.tns.RestApiHttpMethods.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String deposit(Integer id, Double amount) {

        Account account = accountRepository.findById(id).orElse(null);

        if (account == null) {
            throw new RuntimeException("Account Not Found");
        }

        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);

        return "Amount Deposited Successfully";
    }

    @Override
    public String withdraw(Integer id, Double amount) {

        Account account = accountRepository.findById(id).orElse(null);

        if (account == null) {
            throw new RuntimeException("Account Not Found");
        }

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        account.setBalance(account.getBalance() - amount);

        accountRepository.save(account);

        return "Amount Withdrawn Successfully";
    }

    @Override
    public Double getBalance(Integer id) {

        Account account = accountRepository.findById(id).orElse(null);

        if (account == null) {
            throw new RuntimeException("Account Not Found");
        }

        return account.getBalance();
    }

}
