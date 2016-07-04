package com.company.restaurant.application.data.adder;

import com.company.restaurant.application.data.adder.proto.ObjectAdderProto;
import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.controllers.CourseController;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseCategory;
import com.company.util.Util;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class CourseAdder extends ObjectAdderProto<Course> {
    private static final String ENTER_NAME_MESSAGE = "Please, enter course name";
    private static final String ENTER_WEIGHT_MESSAGE = "Please, enter course weight";
    private static final String ENTER_COST_MESSAGE = "Please, enter course cost";

    private CourseController courseController;
    private ObjectChooser<CourseCategory> courseCategoryChooser;

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    public void setCourseCategoryChooser(ObjectChooser<CourseCategory> courseCategoryChooser) {
        this.courseCategoryChooser = courseCategoryChooser;
    }

    @Override
    protected Course addObject() {
        Course result = null;

        String courseName = Util.readInputString(ENTER_NAME_MESSAGE, false);
        if (courseName != null && !courseName.isEmpty()) {
            CourseCategory courseCategory = courseCategoryChooser.chooseObjectFromList();
            if (courseCategory != null) {
                Float weight = Util.readInputPositiveFloat(ENTER_WEIGHT_MESSAGE, true);
                Float cost = Util.readInputPositiveFloat(ENTER_COST_MESSAGE, true);

                Course course = new Course();
                course.setName(courseName.trim());
                course.setCourseCategory(courseCategory);
                course.setWeight(weight);
                course.setCost(cost);

                result = courseController.addCourse(course);
                dataHasBeenSuccessfullyAddedMessage();
            }
        }

        return result;
    }
}
