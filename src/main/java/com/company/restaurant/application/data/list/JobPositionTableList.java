package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.EmployeeController;
import com.company.restaurant.model.JobPosition;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class JobPositionTableList extends ObjectTableListProto<JobPosition> implements ObjectTableList<JobPosition> {
    private static final String[] listHeader = new String[] {
            "Job position id",
            "Job position name"
    };

    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    public Collection<JobPosition> prepareObjectList() {
        return employeeController.findAllJobPositions();
    }

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(JobPosition jobPosition) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(jobPosition.getId()));
        arrayList.add(jobPosition.getName());

        return arrayList.toArray(new String[arrayList.size()]);
    }
}
