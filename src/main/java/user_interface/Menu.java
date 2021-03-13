package user_interface;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Menu {

    protected final String lineBreak = "-----------------------------------------------------";
    protected LinkedHashMap<String, Menu> options;

    private void show() {
        System.out.println(lineBreak);

        Object[] optionNames = options.keySet().toArray();
        for(int i = 0; i < optionNames.length; i++) {
            int index = i + 1;
            String optionName = (String) optionNames[i];

            System.out.println((index) + ". " + optionName);
        }
        System.out.println(lineBreak);
    }

    /**
     * Reads option number from input and returns the option index from linkedhashmap keyset.
     *
     * @return selected option index
     * */
    private int getChoice() {
        Scanner sc = new Scanner(System.in);

        boolean selectionValid = false;
        int choice = -1;

        while(!selectionValid) {
            try {
                System.out.print("Selection: ");

                choice = sc.nextInt() - 1;
                if(choice >= 0 && choice < options.size()) {
                    selectionValid = true;
                }
                else {
                    System.out.println("Please select option from the list above.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Your input must be a number.");
                sc.next();
            }
        }
        return choice;
    }

    /**
     * Shows available options and lets the user choose by typing option number.
     *
     * @return boolean, whether the current menu will be closed
     * */
    public boolean showAndSelect() {//todo mozno bude dobre ak sa vytvori nova superclassa/interface pre tie koncove menu triedy
        show();

        int choice = getChoice();
        String key = (String) options.keySet().toArray()[choice];

        Menu nextMenu = options.get(key);
        if(nextMenu != null) {
            nextMenu.showAndSelect();
            return false;
        }

        return true;
    }
}
