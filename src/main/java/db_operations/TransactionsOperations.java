package db_operations;

import entities.*;
import rdg.Account;
import rdg.AccountFinder;
import rdg.Transaction;
import rdg.TransactionFinder;

import java.sql.SQLException;

public class TransactionsOperations extends Operations {

    @Override
    public void invoke(int choice) throws SQLException {
        switch(choice) {
            case 0:
                printAllTransactionsByAccountNumber();
                break;
//            default:
//                throw Exception("Option is not defined") todo want this here?
        }

    }

    public void printAllTransactionsByAccountNumber() throws SQLException {
        String accountNumber = Utils.getStringFromInput("Enter account number:");

        for(Transaction t: TransactionFinder.getInstance().findAllTransactionsByAccountNumber(accountNumber)) {
            TransactionPrinter.getInstance().print(t);
        }
//        String accountNumber = Utils.getStringFromInput("Enter account number:");
//        Transactions transactions = new Transactions(accountNumber);
//        transactions.print();
    }
}
