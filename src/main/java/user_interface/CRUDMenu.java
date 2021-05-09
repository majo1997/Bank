package user_interface;

import java.util.LinkedHashMap;

public class CRUDMenu extends RoutingMenu {

    public CRUDMenu() {
        options = new LinkedHashMap<>() {{
            put("Customers", new CustomersMenu());
            put("Current accounts", new CurrentAccsMenu());
            put("Savings accounts", new SavingsAccMenu());
            put("Term accounts", new TermAccsMenu());
            put("Currency rates", new CurrenciesMenu());
            put("Transactions", new TransactionsMenu());

            put("Back", null);
        }};
    }
}
