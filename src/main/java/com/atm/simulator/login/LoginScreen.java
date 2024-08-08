package com.atm.simulator.login;

import com.atm.simulator.account.service.AccountService;
import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.service.AtmService;
import com.atm.simulator.utils.InputUtil;

public interface LoginScreen {
    public AccountEntity showLoginScreen();
}
