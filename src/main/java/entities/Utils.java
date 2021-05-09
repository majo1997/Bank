package entities;

import java.math.BigDecimal;
import java.util.Scanner;

public class Utils {
    //todo if empty str is scanned throw Exceotion? primary for loading bigdecimal from string
    public static String getStringFromInput(String message) {
        System.out.print(message + " ");

        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static Integer getIntFromInput(String message) {
        System.out.print(message + " ");

        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }

    public static BigDecimal getBigDecimalFromInput(String message) {//todo remove
        System.out.print(message + " ");

        Scanner sc = new Scanner(System.in);

        return sc.nextBigDecimal();
    }

}
