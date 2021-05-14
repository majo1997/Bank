package printers;

import rdg.ReacquiredCustomerStatistic;

import java.util.Calendar;
import java.util.List;

/**
 * Printer for reacquired customer statistics
 * */
public class ReacquiredCustomersStatisticsPrinter {
    private static final ReacquiredCustomersStatisticsPrinter INSTANCE = new ReacquiredCustomersStatisticsPrinter();

    /**
     * @return instance of reacquired customer statistic printer
     * */
    public static ReacquiredCustomersStatisticsPrinter getInstance() {
        return INSTANCE;
    }

    private ReacquiredCustomersStatisticsPrinter() { }

    /**
     * @param statistics list of statistics to print
     * */
    public void print(List<ReacquiredCustomerStatistic> statistics) {
        System.out.println("year | n | count");
        for(ReacquiredCustomerStatistic s: statistics) {
            System.out.println(s.getYear() + " | " + s.getYearsSinceDeactivated() + " | " + s.getCustomerCount());
        }
    }
}
