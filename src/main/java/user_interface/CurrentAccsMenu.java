package user_interface;

import java.util.LinkedHashMap;

public class CurrentAccsMenu extends Menu {
    public CurrentAccsMenu() {
        options = new LinkedHashMap<>() {{
            //todo in first option remind the acc type...
            put("Print customer accounts", null);
            put("Create new current account", null);
            put("Activate deactivated current account", null);

            put("Back", null);
        }};
    }
}
