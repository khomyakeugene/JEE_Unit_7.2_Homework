package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ItemTableList;
import com.company.restaurant.application.data.list.proto.ItemTableListProto;
import com.company.restaurant.controllers.MenuController;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;

import java.util.Collection;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class MenuCourseTableList extends ItemTableListProto<Menu, Course>
        implements ItemTableList<Menu, Course> {
    private static final String MENU_IS_EMPTY_MESSAGE = "Menu is empty";

    private MenuController menuController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public Collection<Course> prepareItemList(Menu menu) {
        return menuController.findMenuCourses(menu);
    }


    @Override
    protected String[] getListHeader() {
        return CourseTableList.listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Course course) {
        return CourseTableList.getCourseRowData(course);
    }

    @Override
    protected void listDataHasNotBeenFoundMessage() {
        errorMessage(MENU_IS_EMPTY_MESSAGE);
    }
}
