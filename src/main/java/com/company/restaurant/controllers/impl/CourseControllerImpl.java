package com.company.restaurant.controllers;

import com.company.restaurant.dao.CourseCategoryDao;
import com.company.restaurant.dao.CourseDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseCategory;

import java.util.List;

public class CourseControllerImpl implements CourseController {
    private CourseCategoryDao courseCategoryDao;
    private CourseDao courseDao;

    public void setCourseCategoryDao(CourseCategoryDao courseCategoryDao) {
        this.courseCategoryDao = courseCategoryDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public CourseCategory addCourseCategory(String name) {
        return courseCategoryDao.addCourseCategory(name);
    }

    @Override
    public void delCourseCategory(String name) {
        courseCategoryDao.delCourseCategory(name);
    }

    @Override
    public CourseCategory findCourseCategoryByName(String name) {
        return courseCategoryDao.findCourseCategoryByName(name);
    }

    @Override
    public CourseCategory findCourseCategoryById(int CourseCategoryId) {
        return courseCategoryDao.findCourseCategoryById(CourseCategoryId);
    }

    @Override
    public List<CourseCategory> findAllCourseCategories() {
        return courseCategoryDao.findAllCourseCategories();
    }

    @Override
    public Course addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public void delCourse(Course course) {
        courseDao.delCourse(course);
    }

    @Override
    public void delCourse(String name) {
        courseDao.delCourse(name);
    }

    @Override
    public Course findCourseByName(String name) {
        return courseDao.findCourseByName(name);
    }

    @Override
    public Course findCourseById(int courseId) {
        return courseDao.findCourseById(courseId);
    }

    @Override
    public List<Course> findAllCourses() {
        return courseDao.findAllCourses();
    }
}