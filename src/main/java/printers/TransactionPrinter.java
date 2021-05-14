package printers;

import rdg.Transaction;

/**
 * Printer for transactions
 * */
public class TransactionPrinter {
    private static final TransactionPrinter INSTANCE = new TransactionPrinter();

    /**
     * @return instance of transaction printer
     * */
    public static TransactionPrinter getInstance() { return INSTANCE; }

    private TransactionPrinter() { }

    /**
     * @param transaction transaction to print
     * */
    public void print(Transaction transaction) {
        if (transaction == null) {
            throw new NullPointerException("Transaction is null");
        }

        System.out.print("ID: ");
        System.out.println(transaction.getId());
        System.out.print("From ID: ");
        System.out.println(transaction.getFromId());
        System.out.print("To ID: ");
        System.out.println(transaction.getToId());
        System.out.print("From account: ");
        System.out.println(transaction.getFromAccount());
        System.out.print("To account: ");
        System.out.println(transaction.getToAccount());
        System.out.print("Datetime: ");
        System.out.println(transaction.getDatetime());
        System.out.print("Completed: ");
        System.out.println(transaction.isCompleted());
        System.out.print("Type: ");
        System.out.println(transaction.getType());
        System.out.print("Amount: ");
        System.out.println(transaction.getAmount());
        System.out.print("Currency ID: ");
        System.out.println(transaction.getCurrencyId());

    }
}