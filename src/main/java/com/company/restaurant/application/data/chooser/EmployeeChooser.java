package com.company.restaurant.application.data.chooser;

import com.company.restaurant.application.data.chooser.proto.ObjectChooserProto;
import com.company.restaurant.controllers.EmployeeController;
import com.company.restaurant.model.Employee;

/**
 * Created by Yevhen on 27.05.2016.
 */
public class EmployeeChooser extends ObjectChooserProto<Employee, Integer> {
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter employee identifier";

    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    protected Employee findObject(Integer employeeId) {
        return employeeController.findEmployeeById(employeeId);
    }

    @Override
    protected Integer readKeyFieldValue() {
        return readIntegerKeyFieldValue();
    }


    @Override
    protected String getEnterIdentifierMessage() {
        return ENTER_IDENTIFIER_MESSAGE;
    }
}
