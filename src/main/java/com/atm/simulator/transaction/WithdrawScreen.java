package com.atm.simulator.transaction;

import com.atm.simulator.model.WithdrawSummaryModel;

public interface WithdrawScreen {
    public int withdrawScreen();
    public int withdrawOtherScreen();
    public int withdrawSumaryScreen(WithdrawSummaryModel withdrawSummaryModel);
}
