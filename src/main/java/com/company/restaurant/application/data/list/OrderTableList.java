package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.OrderController;
import com.company.restaurant.model.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class OrderTableList extends ObjectTableListProto<Order> implements ObjectTableList<Order> {
    private static final String[] listHeader = new String[] {
            "Order Id",
            "Order number",
            "Order datetime",
            "Status",
            "Personnel name",
            "Table number"
    };

    protected OrderController orderController;

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Order order) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(order.getOrderId()));
        arrayList.add(order.getOrderNumber());
        arrayList.add(simpleDateFormat.format(order.getOrderDatetime().getTime()));
        arrayList.add(order.getState().getName());
        arrayList.add(order.getWaiter().getFirstName() + " " + order.getWaiter().getSecondName());
        arrayList.add(Integer.toString(order.getTable().getNumber()));

        return arrayList.toArray(new String[arrayList.size()]);
    }

    @Override
    public Collection<Order> prepareObjectList() {
        return orderController.findAllOrders();
    }

}
