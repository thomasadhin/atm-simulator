package com.atm.simulator.entity;

public class AccountEntity {
    private String name;
    private String pin;
    private Double balance;
    private String accountNumber;

    public AccountEntity(String accountNumber, Double balance, String name, String pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
        this.pin = pin;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Account Number: " + this.accountNumber + "\n" +
                "Balance: Rp" + this.balance + "\n";
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }

        AccountEntity thatAccount = (AccountEntity) that;

        return this.accountNumber.equals(thatAccount.getAccountNumber());
    }

    public int hashCode() {
        return accountNumber.hashCode() * 23;
    }
}
