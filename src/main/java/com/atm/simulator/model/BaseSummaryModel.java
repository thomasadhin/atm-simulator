package com.atm.simulator.model;

import com.atm.simulator.entity.AccountEntity;

import java.time.LocalDateTime;

public class BaseSummaryModel extends AccountModel{
    private LocalDateTime trxDate;

    public BaseSummaryModel(String accountNumber, Double balance, String name, LocalDateTime trxDate) {
        super(accountNumber, balance, name);
        this.trxDate = trxDate;
    }

    public LocalDateTime getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(LocalDateTime trxDate) {
        this.trxDate = trxDate;
    }
}
