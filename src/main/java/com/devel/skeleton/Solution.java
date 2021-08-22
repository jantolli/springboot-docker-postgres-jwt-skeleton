package com.devel.skeleton;

import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {

    public static boolean validate(String cardNumber, int mod) {

        long checksum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            char number = cardNumber.charAt(i);
            int processedNumber = number % 2 == 0 ? number * 2 : number;
            if (processedNumber > 9) {
                processedNumber = processedNumber - 9;
            }
            checksum += processedNumber;
        }

        if (checksum % mod == 0) {
            return true;
        }
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        System.out.println("sssYES");
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//        int arCount = Integer.parseInt(scanner.nextLine().trim());
//        int[] ar = new int[arCount];
//        String[] arItems = scanner.nextLine().split(" ");
//
//        System.out.println(arItems[0]);
//        System.out.println(Integer.parseInt(arItems[1]));
        long validCardNumbers = 0;
//        String cardLength = arItems[0];
//        Integer mod = Integer.parseInt(arItems[1]);
        String cardLength = "16";
        Integer mod = Integer.parseInt("143");


        System.out.println("sssYES");
        for (long i = 0; i < Math.pow(Double.parseDouble(cardLength),10); i++) {
            System.out.println("Lala" + i);
            if (validate(String.valueOf(i), mod)) {
                System.out.println("YES");
                validCardNumbers++;
            }
        }
        System.out.println((validCardNumbers * (mod/4)) % (10^9 + 7));
    }
}