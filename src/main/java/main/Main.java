package main;

import user_interface.MainMenu;

/**
 * Main class
 * */
public class Main {

    /**
     * Application entry point
     *
     * @param args program arguments
     * */
    public static void main(String[] args) {
        EnvVariables.loadEnvVars();

        DbContext.getConnection();

        MainMenu menu = new MainMenu();

        boolean close = false;
        while(!close) {
            close = menu.showAndSelect();
        }

        DbContext.closeConnection();
    }
}