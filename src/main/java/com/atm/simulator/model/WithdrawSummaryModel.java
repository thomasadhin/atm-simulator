package com.atm.simulator.model;

import com.atm.simulator.entity.AccountEntity;

import java.time.LocalDateTime;

public class WithdrawSummaryModel extends BaseSummaryModel {
    private double withdrawAmount;

    public WithdrawSummaryModel(AccountEntity account, LocalDateTime trxDate, double withdrawAmount) {
        super(account.getAccountNumber(), account.getBalance(), account.getName(), trxDate);
        this.withdrawAmount = withdrawAmount;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}
