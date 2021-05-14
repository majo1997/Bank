package user_interface;

import db_operations.Operations;

import java.sql.SQLException;
import java.text.ParseException;


public class ExecutionMenu extends RoutingMenu {

    /**
     * Shows available options and lets the user choose by typing option number.
     *
     * @return boolean, whether the current menu will be closed
     * */
    public boolean showAndSelect() {//todo mozno bude dobre ak sa vytvori nova superclassa/interface pre tie koncove menu triedy
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
            }//todo catch exceptions here??
            catch(ParseException e) {
                System.out.println("Wrong value/format entered");
            }

            return false;
        }

        return true;
    }

}
