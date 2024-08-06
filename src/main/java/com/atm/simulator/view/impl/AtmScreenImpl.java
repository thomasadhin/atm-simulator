package com.atm.simulator.view.impl;

import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.service.AtmService;
import com.atm.simulator.view.AtmScreen;

import java.util.Scanner;

public class AtmScreenImpl implements AtmScreen {

    private AtmService atmService;
    private Scanner scanner = new Scanner(System.in);

    public AtmScreenImpl(AtmService atmService) {
        this.atmService = atmService;
    }

    @Override
    public void start() {
        loginScreen();
    }

    @Override
    public void loginScreen() {
        AccountEntity account = null;
        while (account == null){
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            account = atmService.validatAccount(accountNumber, pin);
        }
        mainScreen();
    }

    @Override
    public void mainScreen() {
        System.out.println("Welcome to bank");
    }

    @Override
    public void withdrawScreen() {

    }

    @Override
    public void withdrawOtherScreen() {

    }

    @Override
    public void withdrawSumaryScreen() {

    }

    @Override
    public void transferScreen() {

    }

    @Override
    public void transferSumaryScreen() {

    }
}
