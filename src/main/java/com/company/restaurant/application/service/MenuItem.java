package com.company.restaurant.application.service;

import java.util.Map;

/**
 * Created by Yevhen on 25.05.2016.
 */
public interface MenuItem {
    String getItemText();

    Map<Integer, MenuItem> getSubMenu();

    boolean menuAction();

    void performSubMenu();
}
