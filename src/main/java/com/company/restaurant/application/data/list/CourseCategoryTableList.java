package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.CourseController;
import com.company.restaurant.model.CourseCategory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class CourseCategoryTableList extends ObjectTableListProto<CourseCategory>
        implements ObjectTableList<CourseCategory> {
    private static final String[] listHeader = new String[] {
            "Course category id",
            "Course category name"
    };

    private CourseController courseController;

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    @Override
    public Collection<CourseCategory> prepareObjectList() {
        return courseController.findAllCourseCategories();
    }

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(CourseCategory courseCategory) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(courseCategory.getId()));
        arrayList.add(courseCategory.getName());

        return arrayList.toArray(new String[arrayList.size()]);
    }
}
