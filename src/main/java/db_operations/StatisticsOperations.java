package db_operations;

import entities.*;

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
//            default:
//                throw Exception("Option is not defined") todo want this here?, good to have and print here it has not enough options or maybe in exec menu.java
        }
    }

    public void newCustomerCountsStatistics() throws SQLException {
        NewCustomerCountsStatisticsPrinter.getInstance().print(NewCustomerCountsStatisticFinder.getInstance().findAll());
//        ActivationChanges activationChanges = new ActivationChanges();
//        activationChanges.printCustomerCountsByQuaterYears();
    }

    public void ReacquiredCustomersStatistics() throws SQLException {
        Integer n = Utils.getIntFromInput("Enter N");

        ReacquiredCustomersStatisticsPrinter.getInstance().print(ReacquiredCustomerStatisticFinder.getInstance().findAll());

//        Integer maxYearsDeactivated = Utils.getIntFromInput("Enter N:");
//
//        ActivationChanges activationChanges = new ActivationChanges();
//        activationChanges.printReacquiredCustomers(maxYearsDeactivated);
    }
//
//    @Override
//    public void invoke3() {
//
//    }
//
//    @Override
//    public void invoke4() {
//
//    }
//
//    @Override
//    public void invoke5() {
//
//    }
//
//    @Override
//    public void invoke6() {
//
//    }
//
//    @Override
//    public void invoke7() {
//
//    }
//
//    @Override
//    public void invoke8() {
//
//    }
//
//    @Override
//    public void invoke9() {
//
//    }
}
