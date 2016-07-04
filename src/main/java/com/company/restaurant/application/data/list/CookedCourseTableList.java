package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.KitchenController;
import com.company.restaurant.model.CookedCourse;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Employee;
import com.company.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import static com.company.util.Util.toStringMaskNullAsEmpty;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class CookedCourseTableList extends ObjectTableListProto<CookedCourse>
        implements ObjectTableList<CookedCourse> {
    public static final String THERE_ARE_NO_COOKED_COURSES_MESSAGE = "There are no cooked courses";
    public static final String[] listHeader = new String[]{
            "Course Id",
            "Course name",
            "Personnel name",
            "Cooking weight",
            "Cooking datetime"
    };

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private KitchenController kitchenController;

    public void setKitchenController(KitchenController kitchenController) {
        this.kitchenController = kitchenController;
    }

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    public static String[] getCookedCourseRowData(CookedCourse cookedCourse) {
        ArrayList<String> arrayList = new ArrayList<>();

        Course course = cookedCourse.getCourse();
        arrayList.add(Integer.toString(course.getCourseId()));
        arrayList.add(course.getName());
        Employee employee = cookedCourse.getEmployee();
        arrayList.add(employee.getFirstName() + " " + employee.getSecondName());
        arrayList.add(toStringMaskNullAsEmpty(cookedCourse.getWeight()));
        arrayList.add(simpleDateFormat.format(cookedCourse.getCookDatetime().getTime()));

        return arrayList.toArray(new String[arrayList.size()]);
    }

    @Override
    protected String[] dataSetRowDataToStringArray(CookedCourse cookedCourse) {
        return getCookedCourseRowData(cookedCourse);
    }

    @Override
    public Collection<CookedCourse> prepareObjectList() {
        return kitchenController.findAllCookedCourses();
    }

    @Override
    protected void listDataHasNotBeenFoundMessage() {
        Util.printMessage(THERE_ARE_NO_COOKED_COURSES_MESSAGE);
    }
}
