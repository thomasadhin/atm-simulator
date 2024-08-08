package com.atm.simulator.transaction.impl;

import com.atm.simulator.service.AtmService;
import com.atm.simulator.transaction.CheckBalanceScreen;
import com.atm.simulator.utils.InputUtil;

public class CheckBalanceScreenImpl implements CheckBalanceScreen {
    private AtmService atmService;

    public CheckBalanceScreenImpl(AtmService atmService) {
        this.atmService = atmService;
    }

    @Override
    public int showCheckBalance() {
        System.out.println(atmService.checkBalance());
        InputUtil.input("Press Enter to back");
        return 0;
    }
}
