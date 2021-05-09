package entities;

import main.DbContext;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
    private List<Transaction> transactions;

    public Transactions() {
        transactions = new ArrayList<>();
        loadUncompletedTransactions();
    }

    public Transactions(String accountNumber) {
        transactions = new ArrayList<>();
        loadTransactionsByAccountNumber(accountNumber);
    }

    public void loadUncompletedTransactions() {
        String sql = "SELECT * FROM transactions WHERE completed = FALSE";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer transactionId = rs.getInt(1);
                Integer fromId = rs.getInt(2);
                Integer toId = rs.getInt(3);
                String fromAccount = rs.getString(4);
                String toAccount = rs.getString(5);
                Timestamp datetime = rs.getTimestamp(6);
                Boolean completed = rs.getBoolean(7);
                String typeString = rs.getString(8);
                BigDecimal amount = rs.getBigDecimal(9);
                Integer currencyId = rs.getInt(10);

                TransactionType type = TransactionType.valueOf(typeString);

                Transaction t = new Transaction(transactionId,
                        fromId,
                        toId,
                        fromAccount,
                        toAccount,
                        datetime,
                        completed,
                        type,
                        amount,
                        currencyId);
                transactions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dailyClosing() {
        for(Transaction transaction: transactions) {
            try {
                Account fromAccount = new Account(transaction.getFromAccount());
                fromAccount.subtractMoney(transaction.getAmount());

                //todo execute in one transaction
                transaction.setCompleted(true);

                fromAccount.update();
                transaction.update();
            } catch (InvalidValueException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Daily closing successful");

    }

    //todo acc id or acc number??
    private void loadTransactionsByAccountNumber(String accountNumber) { //todo load transactions only on accs in my db or also from another banks??


        //todo get only those not all
        String sql = "SELECT * FROM transactions";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer transactionId = rs.getInt(1);
                Integer fromId = rs.getInt(2);
                Integer toId = rs.getInt(3);
                String fromAccount = rs.getString(4);
                String toAccount = rs.getString(5);
                Timestamp datetime = rs.getTimestamp(6);
                Boolean completed = rs.getBoolean(7);
                String typeString = rs.getString(8);
                BigDecimal amount = rs.getBigDecimal(9);
                Integer currencyId = rs.getInt(10);

                TransactionType type = TransactionType.valueOf(typeString);

                Transaction t = new Transaction(transactionId,
                        fromId,
                        toId,
                        fromAccount,
                        toAccount,
                        datetime,
                        completed,
                        type,
                        amount,
                        currencyId);
                transactions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for(Transaction t: transactions) {
            System.out.println(t);
        }
    }
}
