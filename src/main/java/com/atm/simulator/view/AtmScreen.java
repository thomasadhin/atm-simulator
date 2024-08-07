package com.atm.simulator.view;

import com.atm.simulator.model.TransferSumaryModel;
import com.atm.simulator.model.WithdrawSummaryModel;

public interface AtmScreen {
    public void start();
    public void loginScreen();
    public void mainScreen();
    public void checkBalance();
    public void withdrawScreen();
    public void withdrawOtherScreen();
    public void withdrawSumaryScreen(WithdrawSummaryModel summaryModel);
    public void transferScreen();
    public void transferSumaryScreen(TransferSumaryModel summaryModel);
}
