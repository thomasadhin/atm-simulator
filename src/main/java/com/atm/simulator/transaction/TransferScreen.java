package com.atm.simulator.transaction;

import com.atm.simulator.model.TransferSumaryModel;

public interface TransferScreen {
    public int transferScreen();
    public int tansferConfirmScreen(TransferSumaryModel transferSumaryModel);
    public int transferSumaryScreen(TransferSumaryModel transferSumaryModel);
}
