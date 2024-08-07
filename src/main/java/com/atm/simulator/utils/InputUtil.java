package com.atm.simulator.utils;

import java.util.Scanner;

public class InputUtil {
    static final Scanner scanner = new Scanner(System.in);

    public static String input(String label) {
        if (label!=null){
            System.out.print(label);
        }
        String input = scanner.nextLine();
        return input;
    }
}
