package ui_operations;

import printers.*;
import main.Utils;
import rdg.Account;
import rdg.AccountFinder;
import rdg.AccountType;

import java.math.BigDecimal;
import java.sql.SQLException;

import static main.Utils.uniqueRandomAccountNumber;

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

    }

    public void createCurrentAccount() throws SQLException {
        Account a = new Account();

        a.setAccountNumber(uniqueRandomAccountNumber());
        a.setActive(true);

        BigDecimal balance = new BigDecimal(Utils.getStringFromInput("Enter new balance:"));

        a.setAvailableBalance(balance);
        a.setCurrentBalance(balance);
        a.setAccountType(AccountType.CURRENT.name());

        Integer currencyId = Utils.getIntFromInput("Enter currency ID:");
        a.setCurrencyId(currencyId);
        Integer customerId = Utils.getIntFromInput("Enter customer ID:");
        a.setCustomerId(customerId);

        a.insert();

        System.out.println("Account has been sucessfully created");
        System.out.print("New account ID: ");
        System.out.println(a.getId());

    }

    public void activateDeactivatedCurrentAccount() throws SQLException {
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

    }

}
