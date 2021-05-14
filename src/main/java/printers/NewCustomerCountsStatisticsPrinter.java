package printers;

import rdg.NewCustomerCountsStatistic;

import java.util.List;

/**
 * Printer for new customers counts statistic
 * */
public class NewCustomerCountsStatisticsPrinter {
    private static final NewCustomerCountsStatisticsPrinter INSTANCE = new NewCustomerCountsStatisticsPrinter();

    /**
     * @return instance of new customers count statistic printer
     * */
    public static NewCustomerCountsStatisticsPrinter getInstance() {
        return INSTANCE;
    }

    private NewCustomerCountsStatisticsPrinter() { }

    /**
     * @param statistics list of statistics to print
     * */
    public void print(List<NewCustomerCountsStatistic> statistics) {
        System.out.println("year | quarter | count");
        for(NewCustomerCountsStatistic s: statistics) {
            System.out.println(s.getYear() + " | " + s.getQuadYear() + " | " + s.getCount());
        }
    }
}
