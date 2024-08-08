package com.atm.simulator.service.impl;

import com.atm.simulator.account.service.AccountService;
import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.model.AccountModel;
import com.atm.simulator.model.TransferSumaryModel;
import com.atm.simulator.model.WithdrawSummaryModel;
import com.atm.simulator.service.AtmService;
import com.atm.simulator.utils.Utils;

import java.time.LocalDateTime;

public class AtmServiceImpl implements AtmService {
    private AccountService accountService;
    private AccountEntity currentAccount;

    public AtmServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public AccountEntity validatAccount(String accountNo, String pin) {
        AccountEntity validAccount = accountService.validateAccount(accountNo,pin);
        if (validAccount!=null){
            this.currentAccount = validAccount;
        }
        return validAccount;
    }

    @Override
    public boolean validateBalance(double amount) {
        return amount > currentAccount.getBalance();
    }

    @Override
    public AccountModel checkBalance() {
        AccountModel response = new AccountModel(currentAccount);
        response.setName(currentAccount.getName());
        response.setAccountNumber(currentAccount.getAccountNumber());
        response.setBalance(currentAccount.getBalance());
        return response;
    }

    @Override
    public WithdrawSummaryModel withdraw(double amount) {
        if (!validateBalance(amount)) {
            System.out.println("Insufficient balance $"+amount+".");
            return null;
        }

        double finalBalance = currentAccount.getBalance() - amount;
        currentAccount.setBalance(finalBalance);
        return new WithdrawSummaryModel(currentAccount, LocalDateTime.now(),amount);
    }

    @Override
    public WithdrawSummaryModel withdrawOther(double amount) {
        if (amount>1000){
            System.out.println("Maximum amount to withdraw is $1000\n");
            return null;
        }

        if (amount % 10 != 0){
            System.out.println("Invalid amount\n");
            return null;
        }

        return withdraw(amount);
    }

    @Override
    public TransferSumaryModel transferFund(double amount, String accDest, String reffNumber) {
        if (currentAccount.getBalance() < amount){
            System.out.println("Insufficient balance $" + amount);
            return null;
        }

        AccountEntity destAccount = accountService.getAccountModel(accDest);
        double currAccAmt = currentAccount.getBalance() - amount;
        double destAccAmt = destAccount.getBalance() + amount;
        currentAccount.setBalance(currAccAmt);
        destAccount.setBalance(destAccAmt);


        return new TransferSumaryModel(currentAccount,LocalDateTime.now(),accDest,reffNumber,amount);
    }

    @Override
    public TransferSumaryModel inqTransfer(String strAmount, String accDest, String refNumber) {
        if (!Utils.isNumber(accDest)){
            System.out.println("\nInvalid account");
            return null;
        }

        if (!Utils.isNumber(strAmount)){
            System.out.println("\nInvalid amount");
            return null;
        }

        AccountEntity destAccount = accountService.getAccountModel(accDest);
        if (destAccount==null){
            System.out.println("\nInvalid account");
            return null;
        }

        double amount = Double.parseDouble(strAmount);
        if (validateBalance(amount)){
            System.out.println("\nInsufficient balance $" + amount);
            return null;
        }

        if (amount>1000) {
            System.out.println("\nMaximum amount to transfer is $1000\n");
            return null;
        }

        if (amount < 1){
            System.out.println("\nMinimum amount to transfer is $1\n");
            return null;
        }

        if (!refNumber.isEmpty() && !Utils.isNumber(refNumber)){
            System.out.println("Invalid Reference Number");
            return null;
        }

        if (refNumber.isEmpty()){
            refNumber = Utils.getRandomNumberString();
        }

        return new TransferSumaryModel(currentAccount,LocalDateTime.now(),accDest,refNumber,amount);
    }
}
