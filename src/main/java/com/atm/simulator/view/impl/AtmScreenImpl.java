package com.atm.simulator.view.impl;

import com.atm.simulator.login.LoginScreen;
import com.atm.simulator.login.impl.LoginScreenImpl;
import com.atm.simulator.model.TransferSumaryModel;
import com.atm.simulator.model.WithdrawSummaryModel;
import com.atm.simulator.service.AtmService;
import com.atm.simulator.transaction.ScreenMenuScreen;
import com.atm.simulator.transaction.impl.ScreenMenuScreenImpl;
import com.atm.simulator.utils.InputUtil;
import com.atm.simulator.view.AtmScreen;

public class AtmScreenImpl implements AtmScreen {

    private final LoginScreen loginScreen;
    private final ScreenMenuScreen screenMenuScreen;

    public AtmScreenImpl(AtmService atmService) {
        this.loginScreen = new LoginScreenImpl(atmService);
        this.screenMenuScreen = new ScreenMenuScreenImpl(atmService);
    }

    @Override
    public void start() {
        loginScreen();
    }

    @Override
    public void loginScreen() {
        loginScreen.showLoginScreen();
        mainScreen();
    }

    @Override
    public void mainScreen() {
        screenMenuScreen.showMainScreen();
        loginScreen();
    }
}
