package com.company.restaurant.application.data.viewer;

import com.company.restaurant.application.data.list.EmployeeCookedCourseTableList;
import com.company.restaurant.application.data.list.EmployeeOrderTableList;
import com.company.restaurant.application.data.service.Executor;
import com.company.restaurant.application.data.service.ObjectProcessorProto;
import com.company.restaurant.model.Employee;
import com.company.util.Util;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class EmployeeOrdersAndCookedCoursesViewer
        extends ObjectProcessorProto<Employee> implements Executor {
    private static final String EMPLOYEE_COOKED_COURSES_LIST_PATTERN = "Courses, cooked by <%s>:";
    private static final String EMPLOYEE_ORDERS_LIST_PATTERN = "Orders, processed by <%s>:";

    private EmployeeCookedCourseTableList employeeCookedCourseTableList;
    private EmployeeOrderTableList employeeOrderTableList;

    public void setEmployeeCookedCourseTableList(EmployeeCookedCourseTableList employeeCookedCourseTableList) {
        this.employeeCookedCourseTableList = employeeCookedCourseTableList;
    }

    public void setEmployeeOrderTableList(EmployeeOrderTableList employeeOrderTableList) {
        this.employeeOrderTableList = employeeOrderTableList;
    }

    @Override
    protected String getActionHasBeenSuccessfullyPerformedMessage(Employee object) {
        // No message at all
        return null;
    }

    @Override
    protected String processObject(Employee employee) {
        // Show employee orders
        Util.printMessage(String.format(EMPLOYEE_ORDERS_LIST_PATTERN, employee.getName()));
        employeeOrderTableList.displayItemList(employee);

        // Show employee cooked courses
        Util.printMessage(String.format(EMPLOYEE_COOKED_COURSES_LIST_PATTERN, employee.getName()));
        employeeCookedCourseTableList.displayItemList(employee);

        return null;
    }
}
