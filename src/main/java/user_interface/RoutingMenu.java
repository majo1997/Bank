package user_interface;

import java.util.List;

public class RoutingMenu extends Menu {

    private List<String> breadcrumb;
    /**
     * Shows available options and lets the user choose by typing option number.
     *
     * @return boolean, whether the current menu will be closed
     * */
    public boolean showAndSelect() {
        show();

        int choice = getChoice();
        String key = (String) getOptions().keySet().toArray()[choice];

        RoutingMenu nextMenu = (RoutingMenu) getOptions().get(key);
        if(nextMenu != null) {
            nextMenu.showAndSelect();
            return false;
        }

        return true;
    }


}
