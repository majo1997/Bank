package db_operations;

import entities.*;

public class CurrentAccountsOperations extends Operations {
    @Override
    public void invoke1() {
        Integer customerId = Utils.getIntFromInput("Enter customer ID:");
        Accounts accounts = new Accounts(customerId, AccountType.CURRENT);
        accounts.print();
    }

    @Override
    public void invoke2() {
        Integer customerId = Utils.getIntFromInput("Enter customer ID:");
        Integer currencyId = Utils.getIntFromInput("Enter currency ID:");
        CurrentAccount currentAccount = new CurrentAccount(customerId, currencyId);
        currentAccount.insert();
    }

    @Override
    public void invoke3() {
        Integer accountId = Utils.getIntFromInput("Enter account ID:");

        try {
            CurrentAccount currentAccount = new CurrentAccount(accountId);
            currentAccount.activate();
        } catch (InvalidValueException ex) {
            System.out.println(ex.getMessage());
        }
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
