package db_operations;

import entities.*;
import rdg.CurrencyRate;
import rdg.CurrencyRateFinder;
import rdg.Transaction;
import rdg.TransactionFinder;

import java.math.BigDecimal;
import java.sql.SQLException;

public class CurrenciesOperations extends Operations {

    @Override
    public void invoke(int choice) throws SQLException {//todo rename everywhere choice to option??
        switch(choice) {
            case 0:
                printAllCurrencyRates();
                break;
            case 1:
                createNewCurrencyRate();
                break;
            case 2:
                updateCurrencyRate();
                break;
            case 3:
                deleteCurrencyRate();
                break;
        }
    }

    public void printAllCurrencyRates() throws SQLException {
        for(CurrencyRate cr: CurrencyRateFinder.getInstance().findAllCurrencyRates()) {
            CurrencyRatePrinter.getInstance().print(cr);
        }

//        CurrencyRates currencyRates = new CurrencyRates();
//        currencyRates.print();
    }

    public void createNewCurrencyRate() throws SQLException {
        CurrencyRate cr = new CurrencyRate();
//todo always read values first and continue then
        Integer fromCurrencyId = Utils.getIntFromInput("Enter from currency ID:");
        cr.setFromCurrencyId(fromCurrencyId);
        Integer toCurrencyId = Utils.getIntFromInput("Enter to currency ID:");
        cr.setToCurrencyId(toCurrencyId);
        BigDecimal rate = new BigDecimal(Utils.getStringFromInput("Enter rate:"));
        cr.setRate(rate);

        cr.insert();

        System.out.println("Currency rate has been sucessfully added");
        System.out.print("Currency rate ID: ");
        System.out.println(cr.getId());
//        Integer fromCurrencyId = Utils.getIntFromInput("Enter from currency ID:");
//        Integer toCurrencyId = Utils.getIntFromInput("Enter to currency ID:");
//        BigDecimal rate = new BigDecimal(Utils.getStringFromInput("Enter currency rate:"));
//
//        CurrencyRate currencyRate = new CurrencyRate(fromCurrencyId, toCurrencyId, rate);
//        currencyRate.insert();
//        //todo osetrit ak chcem vlozit existujuci kurz
    }

    public void updateCurrencyRate() throws SQLException {
        Integer fromCurrencyId = Utils.getIntFromInput("Enter from currency ID:");
        Integer toCurrencyId = Utils.getIntFromInput("Enter to currency ID:");

        CurrencyRate cr = CurrencyRateFinder.getInstance().findCurrencyRateByCurrencyIds(fromCurrencyId, toCurrencyId);

        BigDecimal newRate = new BigDecimal(Utils.getStringFromInput("Enter new currency rate:"));
        cr.setRate(newRate);

        cr.update();

//        Integer fromCurrencyId = Utils.getIntFromInput("Enter from currency ID:");
//        Integer toCurrencyId = Utils.getIntFromInput("Enter to currency ID:");
//        BigDecimal newRate = new BigDecimal(Utils.getStringFromInput("Enter new currency rate:"));
//
//        try {
//            CurrencyRate currencyRate = new CurrencyRate(fromCurrencyId, toCurrencyId);
//            currencyRate.changeRate(newRate);
//            currencyRate.update();
//        }
//        catch(InvalidValueException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    public void deleteCurrencyRate() throws SQLException {

        Integer fromCurrencyId = Utils.getIntFromInput("Enter from currency ID:");
        Integer toCurrencyId = Utils.getIntFromInput("Enter to currency ID:");

        CurrencyRate cr = CurrencyRateFinder.getInstance().findCurrencyRateByCurrencyIds(fromCurrencyId, toCurrencyId);


        if (cr == null) {
            System.out.println("Currency rate does not exist");
        } else {
            cr.delete();
            System.out.println("Currency rate has been successfully deleted");
        }
//
//        try {
//            CurrencyRate currencyRate = new CurrencyRate(fromCurrencyId, toCurrencyId);
//            currencyRate.delete();
//        }
//        catch(InvalidValueException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

}
