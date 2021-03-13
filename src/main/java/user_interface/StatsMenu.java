package user_interface;

import java.util.LinkedHashMap;

public class StatsMenu extends Menu {

    public StatsMenu() {
        options = new LinkedHashMap<>() {{
            put("New customers counts", null);
            put("Re-acquired customers", null);

            put("Back", null);
        }};
    }
}
