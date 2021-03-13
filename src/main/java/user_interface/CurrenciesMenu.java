package user_interface;

import java.util.LinkedHashMap;

public class CurrenciesMenu extends Menu {
    public CurrenciesMenu() {
        options = new LinkedHashMap<>() {{
            put("Print all currency rates", null);
            put("Add currency rate between 2 currencies", null);
            put("Update existing currency rate", null);
            put("Delete currency rate", null);

            put("Back", null);//todo when back return 1 level upper so define menu of instance not null
        }};
    }
}
