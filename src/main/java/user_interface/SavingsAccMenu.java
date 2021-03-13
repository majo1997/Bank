package user_interface;

import java.util.LinkedHashMap;

public class SavingsAccMenu extends Menu {
    public SavingsAccMenu() {
        options = new LinkedHashMap<>() {{
            //todo in first option remind the acc type...
            put("Print customer accounts", null);
            put("Create new savings account", null);
            put("Activate deactivated savings account", null);

            put("Back", null);
        }};
    }
}
