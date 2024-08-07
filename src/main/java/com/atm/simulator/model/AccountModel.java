package com.atm.simulator.model;

import com.atm.simulator.entity.AccountEntity;

public class AccountModel {
    private String accountNumber;
    private String name;
    private Double balance;

    public AccountModel(String accountNumber, Double balance, String name) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
    }

    public AccountModel(AccountEntity account) {
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Nama: " + this.name + "\n" +
                "Account Number: " + this.accountNumber + "\n" +
                "Balance: " + this.balance;
    }
}
