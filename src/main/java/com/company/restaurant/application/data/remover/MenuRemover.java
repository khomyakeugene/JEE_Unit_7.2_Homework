package com.company.restaurant.application.data.remover;

import com.company.restaurant.application.data.remover.proto.ObjectRemoverProto;
import com.company.restaurant.controllers.MenuController;
import com.company.restaurant.model.Menu;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class MenuRemover extends ObjectRemoverProto<Menu> {
    private MenuController menuController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    protected void deleteObject(Menu menu) {
        menuController.delMenu(menu);
    }
}
