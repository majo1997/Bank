package user_interface;

import db_operations.CurrenciesOperations;

import java.util.LinkedHashMap;

public class CurrenciesMenu extends ExecutionMenu {
    public CurrenciesMenu() {
        options = new LinkedHashMap<>() {{
            put("Print all currency rates", new CurrenciesOperations());
            put("Add currency rate between 2 currencies", new CurrenciesOperations());
            put("Update existing currency rate", new CurrenciesOperations());
            put("Delete currency rate", new CurrenciesOperations());

            put("Back", null);//todo when back return 1 level upper so define menu of instance not null
        }};
    }
}
