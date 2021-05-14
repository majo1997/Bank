package user_interface;

import db_operations.TermAccountsOperations;

import java.util.LinkedHashMap;

public class TermAccsMenu extends ExecutionMenu {
    public TermAccsMenu() {
        options = new LinkedHashMap<>() {{
            //todo in first option remind the acc type...
            put("Print customer accounts", new TermAccountsOperations());
            put("Create new term account", new TermAccountsOperations());

            put("Back", null);
        }};
    }
}
