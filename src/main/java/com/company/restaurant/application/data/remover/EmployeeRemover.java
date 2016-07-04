package com.company.restaurant.application.data.remover;

import com.company.restaurant.application.data.remover.proto.ObjectRemoverProto;
import com.company.restaurant.controllers.EmployeeController;
import com.company.restaurant.model.Employee;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class EmployeeRemover extends ObjectRemoverProto<Employee> {
    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    protected void deleteObject(Employee employee) {
        employeeController.delEmployee(employee);
    }
}
