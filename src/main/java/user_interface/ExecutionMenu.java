package user_interface;

import db_operations.Operations;


public class ExecutionMenu extends RoutingMenu {

    /**
     * Shows available options and lets the user choose by typing option number.
     *
     * @return boolean, whether the current menu will be closed
     * */
    public boolean showAndSelect() {//todo mozno bude dobre ak sa vytvori nova superclassa/interface pre tie koncove menu triedy
        show();//todo asi bude len choice od 1-9 max

        int choice = getChoice();
        String key = (String) getOptions().keySet().toArray()[choice];

        Operations test = (Operations) getOptions().get(key);
        if(test != null) {
            switch (choice) {
                case 0:
                    test.invoke1();
                    break;
                case 1:
                    test.invoke2();
                    break;
                case 2:
                    test.invoke3();
                    break;
                case 3:
                    test.invoke4();
                    break;
                case 4:
                    test.invoke5();
                    break;
                case 5:
                    test.invoke6();
                    break;
                case 6:
                    test.invoke7();
                    break;
                case 7:
                    test.invoke8();
                    break;
                case 8:
                    test.invoke9();
                    break;
            }
//            test.invoke1();
            return false;
        }

        return true;
    }

}
