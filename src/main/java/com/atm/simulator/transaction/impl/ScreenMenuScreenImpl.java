package com.atm.simulator.transaction.impl;

import com.atm.simulator.misc.Constants;
import com.atm.simulator.service.AtmService;
import com.atm.simulator.transaction.CheckBalanceScreen;
import com.atm.simulator.transaction.ScreenMenuScreen;
import com.atm.simulator.transaction.TransferScreen;
import com.atm.simulator.transaction.WithdrawScreen;
import com.atm.simulator.utils.InputUtil;

public class ScreenMenuScreenImpl implements ScreenMenuScreen {
    private final WithdrawScreen withdrawScreen;
    private final TransferScreen transferScreen;
    private final CheckBalanceScreen checkBalanceScreen;

    public ScreenMenuScreenImpl(AtmService atmService) {
        this.withdrawScreen = new WithdrawScreenImpl(atmService);
        this.transferScreen = new TransferScreenImpl(atmService);
        this.checkBalanceScreen = new CheckBalanceScreenImpl(atmService);
    }

    public void showMainScreen() {
        int opt = 0;
        while (opt!=4){
            System.out.println(
                    "1. Withdraw\n" +
                    "2. Fund Transfer\n" +
                    "3. Check balance\n" +
                    "4. Exit"
            );

            String input = InputUtil.input("Please choose option [3]: ");
            opt = Integer.valueOf(input);

            switch (opt) {
                case Constants.OPT_WITHDRAW :
                    opt = withdrawScreen.withdrawScreen();
                    break;
                case Constants.OPT_TRANSFER:
                    opt = transferScreen.transferScreen();
                    break;
                case Constants.OPT_CHECK_BALANCE:
                    opt = checkBalanceScreen.showCheckBalance();
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
    }
}
