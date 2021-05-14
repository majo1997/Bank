package entities;

import rdg.Account;

public class AccountPrinter {
    private static final AccountPrinter INSTANCE = new AccountPrinter();

    public static AccountPrinter getInstance() { return INSTANCE; }

    private AccountPrinter() { }

    public void print(Account account) {
        if (account == null) {
            throw new NullPointerException("Account is null");
        }

        System.out.print("ID: ");
        System.out.println(account.getId());
        System.out.print("Account number: ");
        System.out.println(account.getAccountNumber());
        System.out.print("Active: ");
        System.out.println(account.getActive());
        System.out.print("Available balance: ");
        System.out.println(account.getAvailableBalance());
        System.out.print("Current balance: ");
        System.out.println(account.getCurrentBalance());
        System.out.print("Account type: ");
        System.out.println(account.getAccountType());
        System.out.print("Interest rate: ");
        System.out.println(account.getInterestRate());
        System.out.print("Commitment till: ");
        System.out.println(account.getCommitmentTill());
        System.out.print("Currency ID: ");
        System.out.println(account.getCurrencyId());
        System.out.print("Customer ID: ");
        System.out.println(account.getCustomerId());
        System.out.print("Current account ID: ");
        System.out.println(account.getCurrentAccountId());

    }
}