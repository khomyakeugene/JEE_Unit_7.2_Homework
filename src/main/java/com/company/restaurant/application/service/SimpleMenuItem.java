package com.company.restaurant.application.service;

import com.company.util.Util;

import java.util.Map;

/**
 * Created by Yevhen on 25.05.2016.
 */
public class SimpleMenuItem implements MenuItem {
    private final static String CODE_MENU_ITEM_PATTERN = "%d. %s";
    private static final String CHOOSE_ACTIVITY_MESSAGE = "Please, choose an activity code:";
    private static final String THERE_ARE_NO_MENU_ITEMS_PATTERN = "There are no menu items in menu <%s>";

    private String itemText;
    private Map<Integer, MenuItem> subMenu;

    @Override
    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public Map<Integer, MenuItem> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Map<Integer, MenuItem> subMenu) {
        this.subMenu = subMenu;
    }

    @Override
    public boolean menuAction() {
        return false;
    }

    private void performMenu(Map<Integer, MenuItem> menu, String menuName) {
        if (menu == null || menu.size() == 0) {
            Util.printMessage(String.format(THERE_ARE_NO_MENU_ITEMS_PATTERN, menuName));
        } else {
            boolean continueProcess = true;
            while (continueProcess) {
                menu.forEach((c, i) -> Util.printMessage(String.format(CODE_MENU_ITEM_PATTERN, c, i.getItemText())));

                MenuItem menuItem = menu.get(Util.parseInt(Util.readInputString(CHOOSE_ACTIVITY_MESSAGE)));
                if (menuItem != null) {
                    Map<Integer, MenuItem> subMenu = menuItem.getSubMenu();
                    if (subMenu != null) {
                        performMenu(subMenu,menuItem.getItemText());
                    } else {
                        continueProcess = menuItem.menuAction();
                    }
                }
            }

        }
    }

    public void performSubMenu() {
        performMenu(subMenu, itemText);
    }
}
