package user_interface;

import ui_operations.SavingsAccountsOperations;

import java.util.LinkedHashMap;

public class SavingsAccMenu extends ExecutionMenu {
    public SavingsAccMenu() {
        options = new LinkedHashMap<>() {{
            put("Print customer savings accounts", new SavingsAccountsOperations());
            put("Create new savings account", new SavingsAccountsOperations());
            put("Activate deactivated savings account", new SavingsAccountsOperations());

            put("Back", null);
        }};
    }
}
