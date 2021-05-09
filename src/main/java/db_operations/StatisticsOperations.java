package db_operations;

import entities.ActivationChanges;
import entities.Utils;

public class StatisticsOperations extends Operations {
    @Override
    public void invoke1() {
        ActivationChanges activationChanges = new ActivationChanges();
        activationChanges.printCustomerCountsByQuaterYears();
    }

    @Override
    public void invoke2() {
        Integer maxYearsDeactivated = Utils.getIntFromInput("Enter N:");

        ActivationChanges activationChanges = new ActivationChanges();
        activationChanges.printReacquiredCustomers(maxYearsDeactivated);
    }

    @Override
    public void invoke3() {

    }

    @Override
    public void invoke4() {

    }

    @Override
    public void invoke5() {

    }

    @Override
    public void invoke6() {

    }

    @Override
    public void invoke7() {

    }

    @Override
    public void invoke8() {

    }

    @Override
    public void invoke9() {

    }
}
