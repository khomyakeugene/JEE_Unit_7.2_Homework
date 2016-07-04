package com.company.restaurant.application.data.chooser;

import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.application.data.chooser.proto.ObjectChooserProto;
import com.company.restaurant.controllers.EmployeeController;
import com.company.restaurant.model.JobPosition;

/**
 * Created by Yevhen on 27.05.2016.
 */
public class JobPositionChooser extends ObjectChooserProto<JobPosition, Integer> implements ObjectChooser<JobPosition> {
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter job position identifier";

    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    protected JobPosition findObject(Integer jobPositionId) {
        return employeeController.findJobPositionById(jobPositionId);
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
