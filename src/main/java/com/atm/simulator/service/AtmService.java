package com.atm.simulator.service;

import com.atm.simulator.account.service.AccountService;
import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.model.AccountModel;
import com.atm.simulator.model.TransferSumaryModel;
import com.atm.simulator.model.WithdrawSummaryModel;

public interface AtmService {
    public AccountEntity validatAccount(String accountNo, String pin);
    public boolean validateBalance(double amount);
    public AccountModel checkBalance();
    public WithdrawSummaryModel withdraw(double amount);
    public WithdrawSummaryModel withdrawOther(double amount);

    TransferSumaryModel transferFund(double amount, String accDest, String reffNumber);

    public TransferSumaryModel inqTransfer(String amount, String accDest, String reffNumber);
}
