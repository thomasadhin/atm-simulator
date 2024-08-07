package com.atm.simulator.view.impl;

import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.misc.Constants;
import com.atm.simulator.model.TransferSumaryModel;
import com.atm.simulator.model.WithdrawSummaryModel;
import com.atm.simulator.service.AtmService;
import com.atm.simulator.utils.InputUtil;
import com.atm.simulator.utils.Utils;
import com.atm.simulator.view.AtmScreen;

import java.util.Scanner;

public class AtmScreenImpl implements AtmScreen {

    private AtmService atmService;

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
            String accountNumber = InputUtil.input("Enter Account Number: ");
            String pin = InputUtil.input("Enter PIN: ");

            account = atmService.validatAccount(accountNumber, pin);
        }
        mainScreen();
    }

    @Override
    public void mainScreen() {
        int opt = 0;

        while (opt!=4){
            System.out.println("1. Withdraw\n" +
                    "2. Fund Transfer\n" +
                    "3. Check balance\n" +
                    "4. Exit");

            String input = InputUtil.input("Please choose option [3]: ");
            opt = Integer.valueOf(input);

            switch (opt) {
                case Constants.OPT_WITHDRAW :
                    withdrawScreen();
                    break;
                case Constants.OPT_TRANSFER:
                    transferScreen();
                    break;
                case Constants.OPT_CHECK_BALANCE:
                    checkBalance();
                    break;
                case Constants.OPT_EXIT:
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
            System.out.println();
        }

        loginScreen();
    }

    @Override
    public void checkBalance() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(atmService.checkBalance());
        System.out.print("Press Enter to back");
        scanner.nextLine();
        mainScreen();
    }

    @Override
    public void withdrawScreen() {
        Scanner scanner = new Scanner(System.in);
        int opt = 0;

        while (opt != 5) {
            System.out.println("1. $10");
            System.out.println("2. $50");
            System.out.println("3. $100");
            System.out.println("4. Other");
            System.out.println("5. Back");
            System.out.print("Please choose option[5]: ");
            opt = scanner.nextInt();

            switch (opt){
                case Constants.OPT_WITHDRAW_OPTION_10:
                    withdrawSumaryScreen(atmService.withdraw(10.0));
                    break;
                case Constants.OPT_WITHDRAW_OPTION_50:
                    withdrawSumaryScreen(atmService.withdraw(50.0));
                    break;
                case Constants.OPT_WITHDRAW_OPTION_100:
                    withdrawSumaryScreen(atmService.withdraw(100.0));
                    break;
                case Constants.OPT_WITHDRAW_OPTION_OTHER:
                    withdrawOtherScreen();
                    break;
                case Constants.OPT_WITHDRAW_OPTION_BACK:
                    mainScreen();
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
        mainScreen();
    }

    @Override
    public void withdrawOtherScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Other Withdraw");
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        withdrawSumaryScreen(atmService.withdrawOther(amount));
    }

    @Override
    public void withdrawSumaryScreen(WithdrawSummaryModel summaryModel) {
        if (summaryModel!=null) {
            System.out.println("Summary");
            System.out.println("Date : " + summaryModel.getTrxDate());
            System.out.println("Withdraw : " + summaryModel.getWithdrawAmount());
            System.out.println("Balance : " + summaryModel.getBalance());
        }

        System.out.println("1. Transaction");
        System.out.println("2. Exit");

        Scanner scanner = new Scanner(System.in);
        int opt = 0;
        while (opt!=2){
            System.out.print("Choose option[2]: ");
            opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    mainScreen();
                    break;
                case 2:
                    loginScreen();
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }

    @Override
    public void transferScreen() {
        System.out.println("Please enter destination account and \n" +
                "press enter to continue or ");
        String accDest = InputUtil.input("press cancel (Esc) to go back to Transaction: ");
        System.out.println("Please enter transfer amount and press enter to continue or");
        String strAmount = InputUtil.input("press enter to go back to Transaction: ");
        String reffNum = Utils.getRandomNumberString();
        System.out.println("Reference Number: "+reffNum);
        InputUtil.input("press enter to continue or press enter to go back to Transaction:");

        transferConfirmationScreen(atmService.inqTransfer(strAmount, accDest, reffNum));
    }

    public void transferConfirmationScreen(TransferSumaryModel sumaryModel) {
        if (sumaryModel!=null) {
            System.out.println("Transfer Confirmation");
            System.out.println("Destination Account\t:" + sumaryModel.getAccountDest());
            System.out.println("Transfer Amount\t\t:" + sumaryModel.getTransferAmount());
            System.out.println("Reference Number\t:" + sumaryModel.getReffNumber());
            System.out.println();
            System.out.println("1. Confirm Trx\n" +
                    "2. Cancel Trx");

            int opt = 0;
            while (opt!=2){
                String input = InputUtil.input("Choose option[2]: ");
                opt = Utils.isNumber(input) ? Integer.parseInt(input) : 0;

                switch (opt) {
                    case 1:
                        transferSumaryScreen(atmService.transferFund(sumaryModel.getTransferAmount(), sumaryModel.getAccountDest(), sumaryModel.getReffNumber()));
                        break;
                    case 2:
                        mainScreen();
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            }
        }
    }

    @Override
    public void transferSumaryScreen(TransferSumaryModel sumaryModel) {
        if (sumaryModel!=null) {
            System.out.println("Transfer Confirmation");
            System.out.println("Destination Account\t:" + sumaryModel.getAccountDest());
            System.out.println("Transfer Amount\t\t:" + sumaryModel.getTransferAmount());
            System.out.println("Reference Number\t:" + sumaryModel.getReffNumber());
            System.out.println();
            System.out.println("1. Transaction\n" +
                    "2. Exit");

            int opt = 0;
            while (opt!=2){
                String input = InputUtil.input("Choose option[2]: ");
                opt = Utils.isNumber(input) ? Integer.parseInt(input) : 0;

                switch (opt) {
                    case 1:
                        mainScreen();
                        break;
                    case 2:
                        loginScreen();
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            }
        }
    }
}
