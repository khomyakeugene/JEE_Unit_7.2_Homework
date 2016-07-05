package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ItemTableList;
import com.company.restaurant.application.data.list.proto.ItemTableListProto;
import com.company.restaurant.controllers.EmployeeController;
import com.company.restaurant.model.Employee;
import com.company.restaurant.model.Order;
import com.company.util.Util;

import java.util.Collection;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class EmployeeOrderTableList extends ItemTableListProto<Employee, Order>
        implements ItemTableList<Employee, Order> {
    private EmployeeController employeeController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    public Collection<Order> prepareItemList(Employee employee) {
        return employeeController.getEmployeeOrders(employee);
    }

    @Override
    protected String[] getListHeader() {
        return OrderTableList.listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Order order) {
        return OrderTableList.getOrderRowData(order);
    }

    @Override
    protected void listDataHasNotBeenFoundMessage() {
        Util.printMessage(OrderTableList.THERE_ARE_NO_ORDERS_MESSAGE);
    }
}
