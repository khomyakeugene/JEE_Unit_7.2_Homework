package com.company.restaurant.application.data.adder;

import com.company.restaurant.application.data.adder.proto.ObjectAdderProto;
import com.company.restaurant.application.data.collector.proto.ItemCollector;
import com.company.restaurant.controllers.MenuController;
import com.company.restaurant.model.Menu;
import com.company.util.Util;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class MenuAdder extends ObjectAdderProto<Menu> {
    private static final String ENTER_NAME_MESSAGE = "Please, enter menu name";

    private MenuController menuController;
    private ItemCollector<Menu> menuCourseCollector;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setMenuCourseCollector(ItemCollector<Menu> menuCourseCollector) {
        this.menuCourseCollector = menuCourseCollector;
    }

    private Menu addMenu() {
        Menu result = null;

        String menuName = Util.readInputString(ENTER_NAME_MESSAGE);
        if (menuName != null && !menuName.isEmpty()) {
            result = menuController.addMenu(menuName.trim());
            dataHasBeenSuccessfullyAddedMessage();
        }

        return result;
    }

    @Override
    protected Menu addObject() {
        Menu result = addMenu();
        menuCourseCollector.addItemsToObject(result);

        return result;
    }
}
