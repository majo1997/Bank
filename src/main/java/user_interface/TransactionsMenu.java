package user_interface;

import java.util.LinkedHashMap;

public class TransactionsMenu extends Menu {
    public TransactionsMenu() {
        options = new LinkedHashMap<>() {{
            put("Print all account transactions", null);

            put("Back", null);
        }};
    }
}
