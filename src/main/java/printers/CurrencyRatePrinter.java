package printers;

import rdg.CurrencyRate;

/**
 * Printer for Currency rates
 * */
public class CurrencyRatePrinter {
    private static final CurrencyRatePrinter INSTANCE = new CurrencyRatePrinter();

    /**
     * @return instance of currency rate printer
     * */
    public static CurrencyRatePrinter getInstance() { return INSTANCE; }

    private CurrencyRatePrinter() { }

    /**
     * @param currencyRate currency rate to print
     * */
    public void print(CurrencyRate currencyRate) {
        if (currencyRate == null) {
            throw new NullPointerException("Currency rate is null");
        }

        System.out.print("ID: ");
        System.out.println(currencyRate.getId());
        System.out.print("From currency ID: ");
        System.out.println(currencyRate.getFromCurrencyId());
        System.out.print("To currency ID: ");
        System.out.println(currencyRate.getToCurrencyId());
        System.out.print("Rate: ");
        System.out.println(currencyRate.getRate());

    }
}