package com.company.restaurant.application.data.remover;

import com.company.restaurant.application.data.remover.proto.ObjectRemoverProto;
import com.company.restaurant.controllers.CourseController;
import com.company.restaurant.model.Course;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class CourseRemover extends ObjectRemoverProto<Course> {
    private CourseController courseController;

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    @Override
    protected void deleteObject(Course course) {
        courseController.delCourse(course);
    }
}
