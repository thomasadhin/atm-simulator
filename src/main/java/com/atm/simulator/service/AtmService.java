package com.atm.simulator.service;

import com.atm.simulator.account.service.AccountService;
import com.atm.simulator.entity.AccountEntity;

public interface AtmService {
    public AccountEntity validatAccount(String accountNo, String pin);
    public boolean validateBalance(double amount);
}
