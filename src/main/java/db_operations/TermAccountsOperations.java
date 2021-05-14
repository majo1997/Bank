package db_operations;

import entities.AccountPrinter;
import entities.AccountType;
import entities.Utils;
import rdg.Account;
import rdg.AccountFinder;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static entities.Utils.uniqueRandomAccountNumber;

public class TermAccountsOperations extends Operations {

    @Override
    public void invoke(int choice) throws SQLException, ParseException {
        switch(choice) {
            case 0:
                printCustomersTermAccounts();
                break;
            case 1:
                createTermAccount();
                break;
        }
    }

    public void printCustomersTermAccounts() throws SQLException {
        Integer customerId = Utils.getIntFromInput("Enter customer ID:");

        for(Account a: AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(customerId, AccountType.TERM)) {
            AccountPrinter.getInstance().print(a);
        }
    }

    public void createTermAccount() throws SQLException, ParseException {
        Account a = new Account();

        a.setAccountNumber(uniqueRandomAccountNumber());
        a.setActive(true);

        BigDecimal balance = new BigDecimal(Utils.getStringFromInput("Enter new balance:"));
        a.setAvailableBalance(balance);
        a.setCurrentBalance(balance);
        a.setAccountType(AccountType.TERM.name());
        BigDecimal interestRate = new BigDecimal(Utils.getStringFromInput("Enter interest rate:")); //todo change all bigdecimals utils to string????? in all accs
        a.setInterestRate(interestRate);

        String commitmentTillString = Utils.getStringFromInput("Enter commitment ending date (dd.mm.yyyy):");
        java.util.Date date = new SimpleDateFormat("dd.MM.yyyy").parse(commitmentTillString);
        java.sql.Date commitmentTill = new java.sql.Date(date.getTime());
        a.setCommitmentTill(commitmentTill);//todo add date commitment!!!!

        Integer currencyId = Utils.getIntFromInput("Enter currency ID:");
        a.setCurrencyId(currencyId);
        Integer customerId = Utils.getIntFromInput("Enter customer ID:");
        a.setCustomerId(customerId);

        a.insert();

        System.out.println("Account has been sucessfully created");
        System.out.print("New account ID: ");
        System.out.println(a.getId());
    }
}
