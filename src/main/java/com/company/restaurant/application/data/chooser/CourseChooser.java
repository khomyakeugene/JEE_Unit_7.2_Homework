package com.company.restaurant.application.data.chooser;

import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.application.data.chooser.proto.ObjectChooserProto;
import com.company.restaurant.controllers.CourseController;
import com.company.restaurant.model.Course;

/**
 * Created by Yevhen on 27.05.2016.
 */
public class CourseChooser extends ObjectChooserProto<Course, Integer>
        implements ObjectChooser<Course> {
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter course identifier";

    private CourseController courseController;

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    @Override
    protected Course findObject(Integer courseId) {
        return courseController.findCourseById(courseId);
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
