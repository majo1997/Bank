package db_operations;

import entities.AccountPrinter;
import entities.AccountType;
import entities.Utils;
import rdg.Account;
import rdg.AccountFinder;

import java.math.BigDecimal;
import java.sql.SQLException;

import static entities.Utils.uniqueRandomAccountNumber;

public class SavingsAccountsOperations extends Operations {

    @Override
    public void invoke(int choice) throws SQLException {
        switch(choice) {
            case 0:
                printCustomersSavingsAccounts();
                break;
            case 1:
                createSavingsAccount();
                break;
            case 2:
                activateDeactivatedSavingsAccount();
                break;
        }
    }

    public void printCustomersSavingsAccounts() throws SQLException {
        Integer customerId = Utils.getIntFromInput("Enter customer ID:");

        for(Account a: AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(customerId, AccountType.SAVINGS)) {
            AccountPrinter.getInstance().print(a);
        }
    }

    public void createSavingsAccount() throws SQLException {
        Account a = new Account();

        a.setAccountNumber(uniqueRandomAccountNumber());
        a.setActive(true);

        BigDecimal balance = new BigDecimal(Utils.getStringFromInput("Enter new balance:"));
        a.setAvailableBalance(balance);
        a.setCurrentBalance(balance);
        a.setAccountType(AccountType.SAVINGS.name());
        BigDecimal interestRate = new BigDecimal(Utils.getStringFromInput("Enter interest rate:")); //todo change all bigdecimals utils to string????? in all accs
        a.setInterestRate(interestRate);

        String currentAccountNumber = Utils.getStringFromInput("Enter current account number:");//todo ak neexistuje ucet - osetrit
        Account currentAccount = AccountFinder.getInstance().findByAccountNumberAndType(currentAccountNumber, AccountType.CURRENT);

        a.setCurrentAccountId(currentAccount.getId());
        a.setCurrencyId(currentAccount.getCurrencyId());
//        Integer customerId = Utils.getIntFromInput("Enter customer ID:");//todo customer id nastavujem tomu kto ma konkretny current acc alebo mam aj toto sledovat?
        a.setCustomerId(currentAccount.getCustomerId());


        a.insert();
        //todo pri prevodoch pozerat kolko za mesiac transakcii z neho sa vykonalo a podla toho uctovat poplatky/ pozriet uctovanie poplatkov este

        System.out.println("Account has been sucessfully created");
        System.out.print("New account ID: ");
        System.out.println(a.getId());
    }

    public void activateDeactivatedSavingsAccount() throws SQLException {
        String accountNumber = Utils.getStringFromInput("Enter savings account number:");

        Account a = AccountFinder.getInstance().findByAccountNumberAndType(accountNumber, AccountType.SAVINGS);

        if(a == null) {
            System.out.println("There is not savings account with entered account number");
        }
        else if(a.getActive()) {
            System.out.println("This account is already activated");
        }
        else {
            a.setActive(true);
            a.update();
            System.out.println("Accout has been activated");
        }
    }
}
