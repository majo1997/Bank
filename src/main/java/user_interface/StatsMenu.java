package user_interface;

import db_operations.StatisticsOperations;

import java.util.LinkedHashMap;

public class StatsMenu extends ExecutionMenu {

    public StatsMenu() {
        options = new LinkedHashMap<>() {{
            put("New customers counts", new StatisticsOperations());
            put("Re-acquired customers", new StatisticsOperations());

            put("Back", null);
        }};
    }
}
