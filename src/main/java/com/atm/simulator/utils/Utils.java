package com.atm.simulator.utils;

import java.util.Random;

public class Utils {
    public static String getRandomNumberString() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    public static Boolean isNumber(String strNum) {
        return strNum.matches("[0-9]+");
    }

    public static Boolean digitCounter(String str, int max) {
        return str.length()==max;
    }
}
