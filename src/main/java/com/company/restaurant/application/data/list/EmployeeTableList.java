package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.EmployeeController;
import com.company.restaurant.model.Employee;

import java.util.ArrayList;
import java.util.Collection;

import static com.company.util.Util.toStringMaskNullAsEmpty;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class EmployeeTableList extends ObjectTableListProto<Employee> implements ObjectTableList<Employee> {
    private static final String[] listHeader = new String[] {
            "Employee Id",
            "Employee first name",
            "Employee second name",
            "Employee job position",
            "Employee phone number",
            "Employee salary"
    };

    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    public Collection<Employee> prepareObjectList() {
        return employeeController.findAllEmployees();
    }

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Employee employee) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(employee.getEmployeeId()));
        arrayList.add(employee.getFirstName());
        arrayList.add(employee.getSecondName());
        arrayList.add(employee.getJobPosition().getName());
        arrayList.add(employee.getPhoneNumber());
        arrayList.add(toStringMaskNullAsEmpty(employee.getSalary()));

        return arrayList.toArray(new String[arrayList.size()]);
    }
}
