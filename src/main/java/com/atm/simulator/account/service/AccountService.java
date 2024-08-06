package com.atm.simulator.account.service;

import com.atm.simulator.entity.AccountEntity;

import java.util.HashMap;
import java.util.Map;

public interface AccountService {
    public AccountEntity getAccountModel(String accountNo);
    public void updateAccount(String accountNo, AccountEntity account);
    public AccountEntity validateAccount(String accountNo, String pin);
}
