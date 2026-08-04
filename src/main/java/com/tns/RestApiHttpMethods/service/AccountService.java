package com.tns.RestApiHttpMethods.service;

public interface AccountService {

    String deposit(Integer id, Double amount);

    String withdraw(Integer id, Double amount);

    Double getBalance(Integer id);

}
