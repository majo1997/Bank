package user_interface;

import ui_operations.TransactionsOperations;

import java.util.LinkedHashMap;

public class TransactionsMenu extends ExecutionMenu {
    public TransactionsMenu() {
        options = new LinkedHashMap<>() {{
            put("Print all account transactions", new TransactionsOperations());

            put("Back", null);
        }};
    }
}
