package user_interface;

import ui_operations.CustomersOperations;

import java.util.LinkedHashMap;

public class CustomersMenu extends ExecutionMenu {

    public CustomersMenu() {
        options = new LinkedHashMap<>() {{
            put("Print list of customers", new CustomersOperations());
            put("Find customer by birth number", new CustomersOperations());
            put("Update personal information of customer", new CustomersOperations());

            put("Back", null);
        }};
    }
}
