package user_interface;

import ui_operations.TermAccountsOperations;

import java.util.LinkedHashMap;

public class TermAccsMenu extends ExecutionMenu {
    public TermAccsMenu() {
        options = new LinkedHashMap<>() {{
            put("Print customer term accounts", new TermAccountsOperations());
            put("Create new term account", new TermAccountsOperations());

            put("Back", null);
        }};
    }
}
