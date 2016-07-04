package com.company.restaurant.controllers;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseCategory;

import java.util.List;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface CourseController {
    CourseCategory addCourseCategory(String name);

    void delCourseCategory(String name);

    CourseCategory findCourseCategoryByName(String name);

    CourseCategory findCourseCategoryById(int CourseCategoryId);

    List<CourseCategory> findAllCourseCategories();

    Course addCourse(Course course);

    void delCourse(Course course);

    void delCourse(String name);

    Course findCourseByName(String name);

    Course findCourseById(int courseId);

    List<Course> findAllCourses();
}
