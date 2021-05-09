package db_operations;

import entities.Transactions;
import entities.Utils;

public class TransactionsOperations extends Operations {
    @Override
    public void invoke1() {
        String accountNumber = Utils.getStringFromInput("Enter account number:");
        Transactions transactions = new Transactions(accountNumber);
        transactions.print();
    }

    @Override
    public void invoke2() {

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
