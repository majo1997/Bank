package user_interface;

import db_operations.Test;

import java.util.LinkedHashMap;

public class CustomersMenu extends ExecutionMenu {

    public CustomersMenu() {
        options = new LinkedHashMap<>() {{
            put("Print list of customers", new Test());
            put("Find customer by birth number", null);
            put("Update personal information of customer", null);

            put("Back", null);
        }};
    }
}
