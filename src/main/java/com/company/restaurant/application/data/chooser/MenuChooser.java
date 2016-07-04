package com.company.restaurant.application.data.chooser;

import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.application.data.chooser.proto.ObjectChooserProto;
import com.company.restaurant.controllers.MenuController;
import com.company.restaurant.model.Menu;

/**
 * Created by Yevhen on 27.05.2016.
 */
public class MenuChooser extends ObjectChooserProto<Menu, Integer> implements ObjectChooser<Menu> {
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter menu identifier";

    private MenuController menuController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    protected Menu findObject(Integer menuId) {
        return menuController.findMenuById(menuId);
    }

    @Override
    protected Integer readKeyFieldValue() {
        return readIntegerKeyFieldValue();
    }


    @Override
    protected String getEnterIdentifierMessage() {
        return ENTER_IDENTIFIER_MESSAGE;
    }

}
