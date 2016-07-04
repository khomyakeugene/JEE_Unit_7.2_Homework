package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.CourseController;
import com.company.restaurant.model.Course;

import java.util.ArrayList;
import java.util.Collection;

import static com.company.util.Util.toStringMaskNullAsEmpty;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class CourseTableList extends ObjectTableListProto<Course> implements ObjectTableList<Course> {
    public static final String[] listHeader = new String[] {
            "Course Id",
            "Course name",
            "Category name",
            "Weight",
            "Cost"
    };

    private CourseController courseController;

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    @Override
    public Collection<Course> prepareObjectList() {
        return courseController.findAllCourses();
    }

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    public static String[] getCourseRowData(Course course) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(course.getCourseId()));
        arrayList.add(course.getName());
        arrayList.add(course.getCourseCategory().getName());
        arrayList.add(toStringMaskNullAsEmpty(course.getWeight()));
        arrayList.add(toStringMaskNullAsEmpty(course.getCost()));

        return arrayList.toArray(new String[arrayList.size()]);
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Course course) {
        return getCourseRowData(course);
    }
}
