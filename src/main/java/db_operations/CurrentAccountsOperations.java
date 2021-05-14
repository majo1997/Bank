package db_operations;

import entities.*;
import rdg.Account;
import rdg.AccountFinder;

import java.math.BigDecimal;
import java.sql.SQLException;

import static entities.Utils.uniqueRandomAccountNumber;

public class CurrentAccountsOperations extends Operations {

    @Override
    public void invoke(int choice) throws SQLException {
        switch(choice) {
            case 0:
                printCustomersCurrentAccounts();
                break;
            case 1:
                createCurrentAccount();
                break;
            case 2:
                activateDeactivatedCurrentAccount();
                break;
        }
    }

    public void printCustomersCurrentAccounts() throws SQLException {
        Integer customerId = Utils.getIntFromInput("Enter customer ID:");

        for(Account a: AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(customerId, AccountType.CURRENT)) {
            AccountPrinter.getInstance().print(a);
        }
//        Integer customerId = Utils.getIntFromInput("Enter customer ID:");
//        Accounts accounts = new Accounts(customerId, AccountType.CURRENT);
//        accounts.print();
    }

    public void createCurrentAccount() throws SQLException {
        //todo chyba nastane ak zadam zle id meny alebo customera treba osetrit
        Account a = new Account();

        a.setAccountNumber(uniqueRandomAccountNumber());
        a.setActive(true);

        BigDecimal balance = new BigDecimal(Utils.getStringFromInput("Enter new balance:"));

        a.setAvailableBalance(balance);
        a.setCurrentBalance(balance);
        a.setAccountType(AccountType.CURRENT.name());
//        a.setInterestRate();
//        a.setCommitmentTill();
        Integer currencyId = Utils.getIntFromInput("Enter currency ID:");
        a.setCurrencyId(currencyId);
        Integer customerId = Utils.getIntFromInput("Enter customer ID:");
        a.setCustomerId(customerId);
//        a.setCurrentAccountId();

        a.insert();

        System.out.println("Account has been sucessfully created");
        System.out.print("New account ID: ");
        System.out.println(a.getId());

//        Integer customerId = Utils.getIntFromInput("Enter customer ID:");
//        Integer currencyId = Utils.getIntFromInput("Enter currency ID:");
//        CurrentAccount currentAccount = new CurrentAccount(customerId, currencyId);
//        currentAccount.insert();
    }

    public void activateDeactivatedCurrentAccount() throws SQLException {
        //todo ak je uz aktivovany tak nic nespravi
        String accountNumber = Utils.getStringFromInput("Enter account number:");

        Account a = AccountFinder.getInstance().findByAccountNumberAndType(accountNumber, AccountType.CURRENT);

        if(a == null) {
            System.out.println("There is no current account with entered account number");
        }
        else if(a.getActive()) {
            System.out.println("This current account is already activated");
        }
        else {
            a.setActive(true);
            a.update();
            System.out.println("Accout has been activated");
        }

//        Integer accountId = Utils.getIntFromInput("Enter account ID:");
//
//        try {
//            CurrentAccount currentAccount = new CurrentAccount(accountId);
//            currentAccount.activate();
//        } catch (InvalidValueException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

}
