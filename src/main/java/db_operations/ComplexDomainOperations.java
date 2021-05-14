package db_operations;

import entities.*;
import ts.*;

import java.math.BigDecimal;
import java.sql.SQLException;

public class ComplexDomainOperations extends Operations {

    @Override
    public void invoke(int choice) throws SQLException {
        switch(choice) {
            case 0:
                moneyTransfer();
                break;
            case 1:
                dailyClosing();
                break;
            case 2:
                monthlyClosing();
                break;
            case 3:
                deactivateAccountByAccountNumber();
                break;
            case 4:
                deactivateCustomerByBirthNumber();
                break;
        }
    }

    public void moneyTransfer() throws SQLException {
        String accountNumberFrom = Utils.getStringFromInput("Enter source account:");
        String accountNumberTo = Utils.getStringFromInput("Enter destination account:");
        BigDecimal amount = new BigDecimal(Utils.getStringFromInput("Enter amount:"));

        try {
            MoneyTransfer.getInstance().moneyTranfer(accountNumberFrom, accountNumberTo, amount);
        }
        catch (Exception e) { //todo want this here???? callexception??
            e.printStackTrace();
        }
    }

    public void dailyClosing() throws SQLException {
        DailyClosing.getInstance().dailyClosing();
    }

    public void monthlyClosing() throws SQLException {
        MonthlyClosing.getInstance().monthlyClosing();
    }

    public void deactivateAccountByAccountNumber() throws SQLException {
        String accountNumber = Utils.getStringFromInput("Enter account number:");

        DeactivateAccount.getInstance().deactivateAccount(accountNumber);
    }

    public void deactivateCustomerByBirthNumber() throws SQLException {
        Integer customerId = Utils.getIntFromInput("Enter customers ID:");

        DeactivateCustomer.getInstance().deactivateAllCustomerAccounts(customerId);
    }

//    @Override
//    public void invoke1() {
//        String fromAccountNumber = Utils.getStringFromInput("Enter source account number:");
//        String toAccountNumber = Utils.getStringFromInput("Enter destination account number:");
//        BigDecimal amount = new BigDecimal(Utils.getStringFromInput("Enter amount to transfer:"));
//
//        Account fromAccount = null;
//        Account toAccount = null;
//        try {
//            fromAccount = new Account(fromAccountNumber);
//        }
//        catch (InvalidValueException ex) {
//            System.out.println(ex.getMessage());
//            return;
//        }
//
//        try {
//            toAccount = new Account(toAccountNumber);
//        }
//        catch (InvalidValueException ignored) {
//        }
//
//        //todo updaty a transfer musia byt v 1 tranzakcii locknute
//        //todo ak je ucet deaktivovany tak sa s nim neda nic robit
//
//        if(fromAccountNumber.equals(toAccountNumber)) {
//            return;
//        }
//
//        Transaction transaction = null;
//
//        //todo
////        if(fromAccount.getAccountType() == AccountType.SAVINGS) {}
//
////        if(fromAccount.getAccountType() == AccountType.TERM) {
////            TermAccount termAccount = new TermAccount(fromAccountNumber);
////            if(!termAccount.commitmentEnded()) {
////                //
////                return;
////            }
////        }
//
//        if(toAccount != null) {
//            transaction = new Transaction(fromAccount.getAccountId(), toAccount.getAccountId(), fromAccountNumber, toAccountNumber, amount, fromAccount.getCurrencyId());
//
//
//            try {
//                fromAccount.transferMoneyTo(toAccount, amount);
//            } catch (InvalidValueException ex) {
//                System.out.println(ex.getMessage());
//                return;
//            }
//
//            fromAccount.update();
//            toAccount.update();
//        }
//        else {
//            transaction = new Transaction(fromAccount.getAccountId(), null, fromAccountNumber, toAccountNumber, amount, fromAccount.getCurrencyId());
//
//            try {
//                fromAccount.transferMoney(amount);
//            } catch (InvalidValueException ex) {
//                System.out.println(ex.getMessage());
//                return;
//            }
//            fromAccount.update();
//        }
//
//        transaction.insert();
//
////        try {
////            fromAccount.transferMoney(amount);
////            //todo pridat transakciu podla
////            //odcitat mozno aj currentbalance nie len availablebalance podla typu transakcie ina bankla/nasa banka
////            //
////            //todo pridat peniaze aj na ucet ak je dest v nasej db a updatenut
////        }
////        catch (InvalidValueException ex) {
////            System.out.println(ex.getMessage());
////        }
//
//        //todo check if there is enough money for tranfer
//        //todo if currency rate for this doesnt exist
//        //todo if account doesnt exist with selected id
//    }
//
//    @Override
//    public void invoke2() {
//        Transactions uncompletedTransactions = new Transactions();
//        uncompletedTransactions.dailyClosing();
//    }

}
