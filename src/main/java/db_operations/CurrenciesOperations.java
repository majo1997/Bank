package db_operations;

import entities.CurrencyRate;
import entities.CurrencyRates;
import entities.InvalidValueException;
import entities.Utils;

import java.math.BigDecimal;

public class CurrenciesOperations extends Operations {
    @Override
    public void invoke1() {
        CurrencyRates currencyRates = new CurrencyRates();
        currencyRates.print();
    }

    @Override
    public void invoke2() {
        Integer fromCurrencyId = Utils.getIntFromInput("Enter from currency ID:");
        Integer toCurrencyId = Utils.getIntFromInput("Enter to currency ID:");
        BigDecimal rate = new BigDecimal(Utils.getStringFromInput("Enter currency rate:"));

        CurrencyRate currencyRate = new CurrencyRate(fromCurrencyId, toCurrencyId, rate);
        currencyRate.insert();
        //todo osetrit ak chcem vlozit existujuci kurz
    }

    @Override
    public void invoke3() {
        Integer fromCurrencyId = Utils.getIntFromInput("Enter from currency ID:");
        Integer toCurrencyId = Utils.getIntFromInput("Enter to currency ID:");
        BigDecimal newRate = new BigDecimal(Utils.getStringFromInput("Enter new currency rate:"));

        try {
            CurrencyRate currencyRate = new CurrencyRate(fromCurrencyId, toCurrencyId);
            currencyRate.changeRate(newRate);
            currencyRate.update();
        }
        catch(InvalidValueException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void invoke4() {
        Integer fromCurrencyId = Utils.getIntFromInput("Enter from currency ID:");
        Integer toCurrencyId = Utils.getIntFromInput("Enter to currency ID:");

        try {
            CurrencyRate currencyRate = new CurrencyRate(fromCurrencyId, toCurrencyId);
            currencyRate.delete();
        }
        catch(InvalidValueException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void invoke5() {

    }

    @Override
    public void invoke6() {

    }

    @Override
    public void invoke7() {

    }

    @Override
    public void invoke8() {

    }

    @Override
    public void invoke9() {

    }
}
