package ui_operations;

import main.Utils;
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
            System.out.println("Money transfer successful");
        }
        catch (TransferException e) {
            System.out.println(e.getMessage());
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

        try {
            DeactivateAccount.getInstance().deactivateAccount(accountNumber);
            System.out.println("Account has been deactivated successfully");
        } catch (DeactivationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deactivateCustomerByBirthNumber() throws SQLException {
        Integer customerId = Utils.getIntFromInput("Enter customers ID:");

        try {
            DeactivateCustomer.getInstance().deactivateAllCustomerAccounts(customerId);
            System.out.println("Customer has been deactivated successfully");
        } catch (DeactivationException e) {
            System.out.println(e.getMessage());
        }
    }

}
