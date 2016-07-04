package com.company.restaurant.application.data.finder;

import com.company.restaurant.application.data.finder.proto.ObjectFinderProto;
import com.company.restaurant.controllers.EmployeeController;
import com.company.restaurant.model.Employee;
import com.company.util.Util;

import java.util.List;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class EmployeeFinder extends ObjectFinderProto<Employee, String> {
    private static final String ENTER_FIRST_NAME_MESSAGE = "Please, enter first name of employee";
    private static final String ENTER_SECOND_NAME_MESSAGE = "Please, enter second name of employee";

    private String firstName;
    private String secondName;

    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    protected List<Employee> findObjects(String objectKeyFieldValue) {
        return employeeController.findEmployeeByFirstAndSecondName(firstName, secondName);
    }

    @Override
    protected String readKeyFieldValue() {
        firstName = Util.readInputString(ENTER_FIRST_NAME_MESSAGE, false);
        secondName = Util.readInputString(ENTER_SECOND_NAME_MESSAGE, false);
        if (firstName != null || secondName != null) {
            if (firstName == null) {
                firstName = "";
            }
            if (secondName == null) {
                secondName = "";
            }
            return (firstName + " " + secondName).trim();
        } else {
            return null;
        }
    }
}
