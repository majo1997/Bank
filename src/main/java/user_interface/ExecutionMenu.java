package user_interface;

import main.DbContext;
import ts.TransferException;
import ui_operations.Operations;

import java.sql.SQLException;
import java.text.ParseException;


public class ExecutionMenu extends RoutingMenu {

    /**
     * Shows available options and lets the user choose by typing option number.
     *
     * @return boolean, whether the current menu will be closed
     * */
    public boolean showAndSelect() {
        show();

        int choice = getChoice();
        String key = (String) getOptions().keySet().toArray()[choice];

        Operations test = (Operations) getOptions().get(key);
        if(test != null) {
            try {
                test.invoke(choice);
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            catch(ParseException e) {
                System.out.println("Wrong value/format entered");
            }

            return false;
        }

        return true;
    }

}
