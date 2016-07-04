package com.company.restaurant.application.data.chooser;

import com.company.restaurant.application.data.chooser.proto.ItemChooser;
import com.company.restaurant.application.data.chooser.proto.ItemChooserProto;
import com.company.restaurant.controllers.MenuController;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class MenuCourseChooser extends ItemChooserProto<Menu, Course, Integer>
        implements ItemChooser<Menu, Course> {
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter course identifier";

    private MenuController menuController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    protected Integer readKeyFieldValue() {
        return readIntegerKeyFieldValue();
    }

    @Override
    protected String getEnterIdentifierMessage() {
        return ENTER_IDENTIFIER_MESSAGE;
    }

    @Override
    protected Course findItem(Menu menu, Integer courseId) {
        return menuController.findMenuCourseByCourseId(menu, courseId);
    }

}
