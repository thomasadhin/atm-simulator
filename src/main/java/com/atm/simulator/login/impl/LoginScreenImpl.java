package com.atm.simulator.login.impl;

import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.login.LoginScreen;
import com.atm.simulator.service.AtmService;
import com.atm.simulator.utils.InputUtil;

public class LoginScreenImpl implements LoginScreen {
    private AtmService atmService;

    public LoginScreenImpl(AtmService atmService) {
        this.atmService = atmService;
    }

    @Override
    public AccountEntity showLoginScreen() {
        AccountEntity account = null;
        while (account == null){
            String accountNumber = InputUtil.input("Enter Account Number: ");
            String pin = InputUtil.input("Enter PIN: ");

            account = atmService.validatAccount(accountNumber, pin);
        }

        return account;
    }
}
