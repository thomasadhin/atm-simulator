package com.atm.simulator.account.service.impl;

import com.atm.simulator.account.service.AccountService;
import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {
    Map<String, AccountEntity> dbAccount = new HashMap<>();

    public AccountServiceImpl(Map<String, AccountEntity> dbAccount) {
        this.dbAccount = dbAccount;
    }

    @Override
    public AccountEntity getAccountModel(String accountNo) {
        return dbAccount.get(accountNo);
    }

    @Override
    public void updateAccount(String accountNo, AccountEntity account) {
        dbAccount.put(accountNo, account);
    }

    @Override
    public AccountEntity validateAccount(String accountNumber, String pin) {
        // validate account number length
        if (!Utils.digitCounter(accountNumber,6)){
            System.out.println("Account Number should have 6 digits length");
            return null;
        }

        // validate account number only accept number
        if (!Utils.isNumber(accountNumber)) {
            System.out.println("Account Number should only contains numbers");
            return null;
        }

        // validate pin length
        if (!Utils.digitCounter(pin,6)) {
            System.out.println("PIN should have 6 digits length");
            return null;
        }

        // validate pin only accept number
        if (!Utils.isNumber(pin)) {
            System.out.println("PIN should only contains numbers");
            return null;
        }

        AccountEntity account = getAccountModel(accountNumber);
        if (account != null && account.getPin().equals(pin)){
            return account;
        }

        System.out.println("Invalid Account Number/PIN");
        return null;
    }
}
