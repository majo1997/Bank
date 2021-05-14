package user_interface;

import db_operations.SavingsAccountsOperations;

import java.util.LinkedHashMap;

public class SavingsAccMenu extends ExecutionMenu {
    public SavingsAccMenu() {
        options = new LinkedHashMap<>() {{
            //todo in first option remind the acc type...
            put("Print customer accounts", new SavingsAccountsOperations());
            put("Create new savings account", new SavingsAccountsOperations());
            put("Activate deactivated savings account", new SavingsAccountsOperations());

            put("Back", null);
        }};
    }
}
