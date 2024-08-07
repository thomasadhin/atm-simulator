package com.atm.simulator.model;

import com.atm.simulator.entity.AccountEntity;

import java.time.LocalDateTime;

public class TransferSumaryModel extends BaseSummaryModel{
    private String accountDest;
    private double transferAmount;
    private String reffNumber;

    public TransferSumaryModel(AccountEntity account, LocalDateTime trxDate, String accountDest, String reffNumber, double transferAmount) {
        super(account.getAccountNumber(), account.getBalance(), account.getName(), trxDate);
        this.accountDest = accountDest;
        this.reffNumber = reffNumber;
        this.transferAmount = transferAmount;
    }

    public String getAccountDest() {
        return accountDest;
    }

    public void setAccountDest(String accountDest) {
        this.accountDest = accountDest;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getReffNumber() {
        return reffNumber;
    }

    public void setReffNumber(String reffNumber) {
        this.reffNumber = reffNumber;
    }
}
