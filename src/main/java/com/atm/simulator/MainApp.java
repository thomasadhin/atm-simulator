package com.atm.simulator;

import com.atm.simulator.account.service.AccountService;
import com.atm.simulator.account.service.impl.AccountServiceImpl;
import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.service.AtmService;
import com.atm.simulator.service.impl.AtmServiceImpl;
import com.atm.simulator.view.AtmScreen;
import com.atm.simulator.view.impl.AtmScreenImpl;

import java.util.HashMap;
import java.util.Map;

public class MainApp {
    public static void main(String[] args) {
        AccountService accountService = initAccount();
        AtmService atmService = new AtmServiceImpl(accountService);
        AtmScreen atmScreen = new AtmScreenImpl(atmService);
        atmScreen.start();
    }

    public static AccountService initAccount() {
        AccountEntity accountEntity1 = new AccountEntity("112233", 100.0, "John Doe", "012108");
        AccountEntity accountEntity2 = new AccountEntity("112244", 30.0, "Jane Doe", "932012");

        Map<String, AccountEntity> accountModelMap = new HashMap<>();
        accountModelMap.put(accountEntity1.getAccountNumber(), accountEntity1);
        accountModelMap.put(accountEntity2.getAccountNumber(), accountEntity2);

        return new AccountServiceImpl(accountModelMap);
    }
}
