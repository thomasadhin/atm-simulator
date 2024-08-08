package com.atm.simulator.account.service.impl;

import com.atm.simulator.account.service.AccountService;
import com.atm.simulator.entity.AccountEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    public AccountService init() {
        AccountEntity accountModel1 = new AccountEntity("112233", 100.0, "John Doe", "012108");
        AccountEntity accountModel2 = new AccountEntity("112244", 30.0, "Jane Doe", "932012");

        Map<String, AccountEntity> accountModelMap = new HashMap<>();
        accountModelMap.put(accountModel1.getAccountNumber(), accountModel1);
        accountModelMap.put(accountModel2.getAccountNumber(), accountModel2);

        return new AccountServiceImpl(accountModelMap);
    }

    @Test
    public void validateAccount_accountlengthNoInvalid() {
        AccountService accountService = init();
        AccountEntity accountModel = accountService.validateAccount("12345","123456");
        Assertions.assertNull(accountModel);
    }

    @Test
    public void validateAccount_accountNoInvalid2() {
        AccountService accountService = init();
        AccountEntity accountModel = accountService.validateAccount("12345a","123456");
        Assertions.assertNull(accountModel);
    }

    @Test
    public void validateAccount_pinLengthInvalid() {
        AccountService accountService = init();
        AccountEntity accountModel = accountService.validateAccount("112233","12346");
        Assertions.assertNull(accountModel);
    }

    @Test
    public void validateAccount_pinInvalid() {
        AccountService accountService = init();
        AccountEntity accountModel = accountService.validateAccount("112233","1234a6");
        Assertions.assertNull(accountModel);
    }

    @Test
    public void validateAccount_accountNoNotFound() {
        AccountService accountService = init();
        AccountEntity accountModel = accountService.validateAccount("112231","012108");
        Assertions.assertNull(accountModel);
    }

    @Test
    public void validateAccount_pinWrong() {
        AccountService accountService = init();
        AccountEntity accountModel = accountService.validateAccount("112233","012109");
        Assertions.assertNull(accountModel);
    }

    @Test
    public void validateAccount_success() {
        AccountService accountService = init();
        AccountEntity accountModel = accountService.validateAccount("112233","012108");
        Assertions.assertNotNull(accountModel);
    }
}