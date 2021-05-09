package user_interface;

import java.util.LinkedHashMap;

public class TermAccsMenu extends ExecutionMenu {
    public TermAccsMenu() {
        options = new LinkedHashMap<>() {{
            //todo in first option remind the acc type...
            put("Print customer accounts", null);
            put("Create new term account", null);

            put("Back", null);
        }};
    }
}
