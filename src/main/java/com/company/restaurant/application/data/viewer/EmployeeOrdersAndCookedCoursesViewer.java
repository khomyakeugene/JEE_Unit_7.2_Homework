package com.company.restaurant.application.data.viewer;

import com.company.restaurant.application.data.list.EmployeeCookedCourseTableList;
import com.company.restaurant.application.data.service.Executor;
import com.company.restaurant.application.data.service.ObjectProcessorProto;
import com.company.restaurant.model.Employee;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class EmployeeOrdersAndCookedCoursesViewer
        extends ObjectProcessorProto<Employee> implements Executor {

    private EmployeeCookedCourseTableList employeeCookedCourseTableList;

    public void setEmployeeCookedCourseTableList(EmployeeCookedCourseTableList employeeCookedCourseTableList) {
        this.employeeCookedCourseTableList = employeeCookedCourseTableList;
    }

    @Override
    protected String getActionHasBeenSuccessfullyPerformedMessage(Employee object) {
        // No message at all
        return null;
    }

    @Override
    protected String processObject(Employee employee) {
        // Show employee orders

        // Show employee cooked courses
        employeeCookedCourseTableList.displayObjectList();

        return null;
    }
}
