package user_interface;

import db_operations.CurrentAccountsOperations;

import java.util.LinkedHashMap;

public class CurrentAccsMenu extends ExecutionMenu {
    public CurrentAccsMenu() {
        options = new LinkedHashMap<>() {{
            //todo in first option remind the acc type...
            put("Print customer accounts", new CurrentAccountsOperations());
            put("Create new current account", new CurrentAccountsOperations());
            put("Activate deactivated current account", new CurrentAccountsOperations());

            put("Back", null);
        }};
    }
}
