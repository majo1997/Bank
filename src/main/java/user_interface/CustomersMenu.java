package user_interface;

import db_operations.CustomersOperations;

import java.util.LinkedHashMap;

public class CustomersMenu extends ExecutionMenu {

    public CustomersMenu() {
        //todo define one class for each execlass
        options = new LinkedHashMap<>() {{
            put("Print list of customers", new CustomersOperations());
            put("Find customer by birth number", new CustomersOperations());
            put("Update personal information of customer", new CustomersOperations());

            put("Back", null);
        }};
    }
}
