package user_interface;

import java.util.LinkedHashMap;

public class MainMenu extends Menu {
    //todo add breadcrumbs to all menus separated by '>' or some other character?
    public MainMenu() {
        options = new LinkedHashMap<>() {{
            put("CRUD operations", new CRUDMenu());
            put("Complex domain operations", new DomOpsMenu());
            put("Statistics", new StatsMenu());

            put("Exit", null);
        }};
    }




}
