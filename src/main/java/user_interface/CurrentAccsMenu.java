package user_interface;

import ui_operations.CurrentAccountsOperations;

import java.util.LinkedHashMap;

public class CurrentAccsMenu extends ExecutionMenu {
    public CurrentAccsMenu() {
        options = new LinkedHashMap<>() {{
            put("Print customer accounts", new CurrentAccountsOperations());
            put("Create new current account", new CurrentAccountsOperations());
            put("Activate deactivated current account", new CurrentAccountsOperations());

            put("Back", null);
        }};
    }
}
