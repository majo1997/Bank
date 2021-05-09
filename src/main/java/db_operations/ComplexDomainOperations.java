package db_operations;

import entities.*;

import java.math.BigDecimal;

public class ComplexDomainOperations extends Operations {
    @Override
    public void invoke1() {
        String fromAccountNumber = Utils.getStringFromInput("Enter source account number:");
        String toAccountNumber = Utils.getStringFromInput("Enter destination account number:");
        BigDecimal amount = new BigDecimal(Utils.getStringFromInput("Enter amount to transfer:"));

        Account fromAccount = null;
        Account toAccount = null;
        try {
            fromAccount = new Account(fromAccountNumber);
        }
        catch (InvalidValueException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        try {
            toAccount = new Account(toAccountNumber);
        }
        catch (InvalidValueException ignored) {
        }

        //todo updaty a transfer musia byt v 1 tranzakcii locknute
        //todo ak je ucet deaktivovany tak sa s nim neda nic robit

        if(fromAccountNumber.equals(toAccountNumber)) {
            return;
        }

        Transaction transaction = null;

        //todo
//        if(fromAccount.getAccountType() == AccountType.SAVINGS) {}

//        if(fromAccount.getAccountType() == AccountType.TERM) {
//            TermAccount termAccount = new TermAccount(fromAccountNumber);
//            if(!termAccount.commitmentEnded()) {
//                //
//                return;
//            }
//        }

        if(toAccount != null) {
            transaction = new Transaction(fromAccount.getAccountId(), toAccount.getAccountId(), fromAccountNumber, toAccountNumber, amount, fromAccount.getCurrencyId());


            try {
                fromAccount.transferMoneyTo(toAccount, amount);
            } catch (InvalidValueException ex) {
                System.out.println(ex.getMessage());
                return;
            }

            fromAccount.update();
            toAccount.update();
        }
        else {
            transaction = new Transaction(fromAccount.getAccountId(), null, fromAccountNumber, toAccountNumber, amount, fromAccount.getCurrencyId());

            try {
                fromAccount.transferMoney(amount);
            } catch (InvalidValueException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            fromAccount.update();
        }

        transaction.insert();

//        try {
//            fromAccount.transferMoney(amount);
//            //todo pridat transakciu podla
//            //odcitat mozno aj currentbalance nie len availablebalance podla typu transakcie ina bankla/nasa banka
//            //
//            //todo pridat peniaze aj na ucet ak je dest v nasej db a updatenut
//        }
//        catch (InvalidValueException ex) {
//            System.out.println(ex.getMessage());
//        }

        //todo check if there is enough money for tranfer
        //todo if currency rate for this doesnt exist
        //todo if account doesnt exist with selected id
    }

    @Override
    public void invoke2() {
        Transactions uncompletedTransactions = new Transactions();
        uncompletedTransactions.dailyClosing();
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
