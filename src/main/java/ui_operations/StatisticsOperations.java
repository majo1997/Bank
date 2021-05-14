package ui_operations;

import printers.*;
import main.Utils;
import rdg.NewCustomerCountsStatisticFinder;
import rdg.ReacquiredCustomerStatisticFinder;

import java.sql.SQLException;

public class StatisticsOperations extends Operations {
    @Override
    public void invoke(int choice) throws SQLException {
        switch(choice) {
            case 0:
                newCustomerCountsStatistics();
                break;
            case 1:
                ReacquiredCustomersStatistics();
                break;
        }
    }

    public void newCustomerCountsStatistics() throws SQLException {
        NewCustomerCountsStatisticsPrinter.getInstance().print(NewCustomerCountsStatisticFinder.getInstance().findAll());

    }

    public void ReacquiredCustomersStatistics() throws SQLException {
        Integer n = Utils.getIntFromInput("Enter N:");

        ReacquiredCustomersStatisticsPrinter.getInstance().print(ReacquiredCustomerStatisticFinder.getInstance().findAll());

    }

}
