package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ItemTableList;
import com.company.restaurant.application.data.list.proto.ItemTableListProto;
import com.company.restaurant.controllers.EmployeeController;
import com.company.restaurant.model.CookedCourse;
import com.company.restaurant.model.Employee;
import com.company.util.Util;

import java.util.Collection;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class EmployeeCookedCourseTableList extends ItemTableListProto<Employee, CookedCourse>
        implements ItemTableList<Employee, CookedCourse> {
    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    public Collection<CookedCourse> prepareItemList(Employee employee) {
        return employeeController.getEmployeeCookedCourses(employee);
    }

    @Override
    protected String[] getListHeader() {
        return CookedCourseTableList.listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(CookedCourse cookedCourse) {
        return CookedCourseTableList.getCookedCourseRowData(cookedCourse);
    }

    @Override
    protected void listDataHasNotBeenFoundMessage() {
        Util.printMessage(CookedCourseTableList.THERE_ARE_NO_COOKED_COURSES_MESSAGE);
    }

}
