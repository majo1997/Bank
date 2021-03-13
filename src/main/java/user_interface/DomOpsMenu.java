package user_interface;

import java.util.LinkedHashMap;

public class DomOpsMenu extends Menu {

    public DomOpsMenu() {
        options = new LinkedHashMap<>() {{
            put("Transfer money between 2 accounts", null);
            put("Daily closing", null);
            put("End-month closing", null);
            put("Account deactivation", null);
            put("Customer deactivation", null);

            put("Back", null);
        }};
    }
}
