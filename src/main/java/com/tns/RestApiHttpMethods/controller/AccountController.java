package com.tns.RestApiHttpMethods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tns.RestApiHttpMethods.dto.AmountRequest;
import com.tns.RestApiHttpMethods.service.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{id}/deposit")
    public ResponseEntity<String> deposit(@PathVariable Integer id,
                                          @RequestBody AmountRequest request) {

        String message = accountService.deposit(id, request.getAmount());

        return ResponseEntity.ok(message);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Integer id,
                                           @RequestBody AmountRequest request) {

        String message = accountService.withdraw(id, request.getAmount());

        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Integer id) {

        Double balance = accountService.getBalance(id);

        return ResponseEntity.ok(balance);
    }
}