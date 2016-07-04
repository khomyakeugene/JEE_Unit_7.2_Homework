package com.company.restaurant.application;

import com.company.restaurant.application.service.MenuItem;
import com.company.util.Util;

/**
 * Created by Yevhen on 24.05.2016.
 */
public class RestaurantConsoleApplication {
    private static final String TITLE_MESSAGE = "Welcome to the restaurant application!";
    private static final String FAREWELL_MESSAGE = "Thank you for use our application";

    private MenuItem mainMenu;

    public void setMainMenu(MenuItem mainMenu) {
        this.mainMenu = mainMenu;
    }

    private void mainMenuProcess() {
        mainMenu.performSubMenu();
    }

    public void run() {
        Util.printMessage(TITLE_MESSAGE);
        mainMenuProcess();
        Util.printMessage(FAREWELL_MESSAGE);
    }
}
