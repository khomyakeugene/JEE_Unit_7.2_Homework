package com.company.restaurant.application.data.chooser;

import com.company.restaurant.application.data.chooser.proto.ObjectChooserProto;
import com.company.restaurant.controllers.CourseController;
import com.company.restaurant.model.CourseCategory;

/**
 * Created by Yevhen on 27.05.2016.
 */
public class CourseCategoryChooser extends ObjectChooserProto<CourseCategory, Integer> {
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter course category identifier";

    private CourseController courseController;

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    @Override
    protected CourseCategory findObject(Integer courseCategoryId) {
        return courseController.findCourseCategoryById(courseCategoryId);
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
