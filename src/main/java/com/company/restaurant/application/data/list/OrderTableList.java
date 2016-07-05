package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.OrderController;
import com.company.restaurant.model.Order;
import com.company.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class OrderTableList extends ObjectTableListProto<Order> implements ObjectTableList<Order> {
    public static final String THERE_ARE_NO_ORDERS_MESSAGE = "There are no orders";
    public static final String[] listHeader = new String[] {
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

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    public static String[] getOrderRowData(Order order) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(order.getOrderId()));
        arrayList.add(order.getOrderNumber());
        arrayList.add(simpleDateFormat.format(order.getOrderDatetime().getTime()));
        arrayList.add(order.getState().getName());
        arrayList.add(order.getWaiter().getName());
        arrayList.add(Integer.toString(order.getTable().getNumber()));

        return arrayList.toArray(new String[arrayList.size()]);
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Order order) {
        return getOrderRowData(order);
    }

    @Override
    public Collection<Order> prepareObjectList() {
        return orderController.findAllOrders();
    }

    @Override
    protected void listDataHasNotBeenFoundMessage() {
        Util.printMessage(THERE_ARE_NO_ORDERS_MESSAGE);
    }
}
