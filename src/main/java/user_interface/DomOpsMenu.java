package user_interface;

import ui_operations.ComplexDomainOperations;

import java.util.LinkedHashMap;

public class DomOpsMenu extends ExecutionMenu {

    public DomOpsMenu() {
        options = new LinkedHashMap<>() {{
            put("Transfer money between 2 accounts", new ComplexDomainOperations());
            put("Daily closing", new ComplexDomainOperations());
            put("End-month closing", new ComplexDomainOperations());
            put("Account deactivation", new ComplexDomainOperations());
            put("Customer deactivation", new ComplexDomainOperations());

            put("Back", null);
        }};
    }
}
