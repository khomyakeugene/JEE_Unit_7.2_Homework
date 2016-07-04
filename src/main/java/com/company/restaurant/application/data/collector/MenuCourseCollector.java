package com.company.restaurant.application.data.collector;

import com.company.restaurant.application.data.collector.proto.ItemCollector;
import com.company.restaurant.application.data.collector.proto.ItemCollectorProto;
import com.company.restaurant.controllers.CourseController;
import com.company.restaurant.controllers.MenuController;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;

/**
 * Created by Yevhen on 27.05.2016.
 */
public class MenuCourseCollector extends ItemCollectorProto<Menu, Course, Course>
        implements ItemCollector<Menu> {

    private CourseController courseController;
    private MenuController menuController;

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    protected void addItemToObject(Menu menu, Course course) {
        menuController.addCourseToMenu(menu, course);
    }

    @Override
    protected void delItemFromObject(Menu menu, Course course) {
        menuController.delCourseFromMenu(menu,
                courseController.findCourseById(course.getCourseId()));
    }

}
