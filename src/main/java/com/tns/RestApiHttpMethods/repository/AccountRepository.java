package com.tns.RestApiHttpMethods.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tns.RestApiHttpMethods.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}