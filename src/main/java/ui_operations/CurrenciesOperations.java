package ui_operations;

import printers.*;
import main.Utils;
import rdg.CurrencyRate;
import rdg.CurrencyRateFinder;

import java.math.BigDecimal;
import java.sql.SQLException;

public class CurrenciesOperations extends Operations {

    @Override
    public void invoke(int choice) throws SQLException {
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

    }

    public void createNewCurrencyRate() throws SQLException {
        CurrencyRate cr = new CurrencyRate();
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

    }

    public void updateCurrencyRate() throws SQLException {
        Integer fromCurrencyId = Utils.getIntFromInput("Enter from currency ID:");
        Integer toCurrencyId = Utils.getIntFromInput("Enter to currency ID:");

        CurrencyRate cr = CurrencyRateFinder.getInstance().findCurrencyRateByCurrencyIds(fromCurrencyId, toCurrencyId);

        BigDecimal newRate = new BigDecimal(Utils.getStringFromInput("Enter new currency rate:"));
        cr.setRate(newRate);

        cr.update();

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

    }

}
