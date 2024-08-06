package com.atm.simulator.service.impl;

import com.atm.simulator.account.service.AccountService;
import com.atm.simulator.account.service.impl.AccountServiceImpl;
import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.service.AtmService;

public class AtmServiceImpl implements AtmService {
    private AccountService accountService;
    private AccountEntity currentAccount;

    public AtmServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public AccountEntity validatAccount(String accountNo, String pin) {
        AccountEntity validAccount = accountService.validateAccount(accountNo,pin);
        if (validAccount!=null){
            this.currentAccount = validAccount;
        }
        return validAccount;
    }

    @Override
    public boolean validateBalance(double amount) {
        return amount < currentAccount.getBalance();
    }
}
