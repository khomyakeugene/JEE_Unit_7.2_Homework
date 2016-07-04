package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.MenuController;
import com.company.restaurant.model.Menu;
import com.company.util.Util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class MenuTableList extends ObjectTableListProto<Menu> implements ObjectTableList<Menu> {
    private static final String THERE_ARE_NO_MENUS_MESSAGE = "There are no menus";
    private static final String[] listHeader = new String[] {
            "Menu Id",
            "Menu name"
    };

    private MenuController menuController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public Collection<Menu> prepareObjectList() {
        return menuController.findAllMenus();
    }

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Menu menu) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(menu.getId()));
        arrayList.add(menu.getName());

        return arrayList.toArray(new String[arrayList.size()]);
    }

    @Override
    protected void listDataHasNotBeenFoundMessage() {
        Util.printMessage(THERE_ARE_NO_MENUS_MESSAGE);
    }
}
