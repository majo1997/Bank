package entities;

import java.util.List;

public class NewCustomerCountsStatisticsPrinter {
    private static final NewCustomerCountsStatisticsPrinter INSTANCE = new NewCustomerCountsStatisticsPrinter();

    public static NewCustomerCountsStatisticsPrinter getInstance() {
        return INSTANCE;
    }

    private NewCustomerCountsStatisticsPrinter() { }

    public void print(List<NewCustomerCountsStatistic> statistics) {
        System.out.println("year | quarter | count");
        for(NewCustomerCountsStatistic s: statistics) {
            System.out.println(s.getYear() + " | " + s.getQuadYear() + " | " + s.getCount());
        }

        //todo refactor with vvvvvvvv
//        int column1Width = 0;
//        int column2Width = 0 ;
//        for (FreeCallStatistic s : statistics) {
//            column1Width = Math.max(column1Width, Integer.toString(s.getN()).length());
//            column2Width = Math.max(column2Width, s.getDelta().toString().length());
//        }
//
//        System.out.print("n");
//        for (int i = 0; i < column1Width - 1; ++i) {
//            System.out.print(" ");
//        }
//        System.out.println(" | delta");
//        for (int i = 0; i < column1Width + column2Width + 3; ++i) {
//            System.out.print("-");
//        }
//        System.out.println();
//
//        for (FreeCallStatistic s : statistics) {
//            String nString = Integer.toString(s.getN());
//            int nLength = nString.length();
//
//            System.out.print(s.getN());
//
//            int spaceLength = column1Width - nLength;
//            for (int i = 0; i < spaceLength; ++i) {
//                System.out.print(" ");
//            }
//            System.out.print(" | ");
//            System.out.println(s.getDelta());
//        }
    }
}
