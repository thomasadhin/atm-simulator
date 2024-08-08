package com.atm.simulator.transaction.impl;

import com.atm.simulator.misc.Constants;
import com.atm.simulator.model.WithdrawSummaryModel;
import com.atm.simulator.service.AtmService;
import com.atm.simulator.transaction.WithdrawScreen;
import com.atm.simulator.utils.InputUtil;
import com.atm.simulator.utils.Utils;

public class WithdrawScreenImpl implements WithdrawScreen {
    private final AtmService atmService;

    public WithdrawScreenImpl(AtmService atmService) {
        this.atmService = atmService;
    }

    @Override
    public int withdrawScreen() {
        int response = 0;
        int opt = 0;

        while (opt == 0) {
            System.out.println("1. $10");
            System.out.println("2. $50");
            System.out.println("3. $100");
            System.out.println("4. Other");
            System.out.println("5. Back");
            String input = InputUtil.input("Please choose option[5]: ");
            opt = Utils.isNumber(input) ? Integer.parseInt(input) : 0;

            switch (opt){
                case Constants.OPT_WITHDRAW_OPTION_10:
                    response =  withdrawSumaryScreen(atmService.withdraw(10.0));
                    break;
                case Constants.OPT_WITHDRAW_OPTION_50:
                    response =  withdrawSumaryScreen(atmService.withdraw(50.0));
                    break;
                case Constants.OPT_WITHDRAW_OPTION_100:
                    response =  withdrawSumaryScreen(atmService.withdraw(100.0));
                    break;
                case Constants.OPT_WITHDRAW_OPTION_OTHER:
                    response =  withdrawOtherScreen();
                    break;
                case Constants.OPT_WITHDRAW_OPTION_BACK:
                    break;
                default:
                    System.out.println("Invalid command");
                    opt=0;
                    break;
            }
        }
        return response;
    }

    @Override
    public int withdrawOtherScreen() {
        System.out.println("Other Withdraw");
        String input = InputUtil.input("Enter amount to withdraw: ");
        double amount = Utils.isNumber(input) ? Double.parseDouble(input) : 0.0;

        return withdrawSumaryScreen(atmService.withdrawOther(amount));
    }

    @Override
    public int withdrawSumaryScreen(WithdrawSummaryModel summaryModel) {
        int result = 0;
        if (summaryModel!=null) {
            System.out.println("Summary");
            System.out.println("Date : " + summaryModel.getTrxDate());
            System.out.println("Withdraw : " + summaryModel.getWithdrawAmount());
            System.out.println("Balance : " + summaryModel.getBalance());
        }

        System.out.println("1. Transaction");
        System.out.println("2. Exit");

        int opt = 0;
        while (opt==0){
            String input = InputUtil.input("Choose option[2]: ");
            opt = Utils.isNumber(input) ? Integer.parseInt(input) : 0;

            switch (opt) {
                case 1:
                    result = 0;
                    break;
                case 2:
                    result = 4;
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Invalid command");
                    opt=0;
                    break;
            }
        }
        return result;
    }


}
