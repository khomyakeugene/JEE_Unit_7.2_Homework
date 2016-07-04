package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.model.Order;

import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class ClosedOrderTableList extends OrderTableList implements ObjectTableList<Order> {
    @Override
    public Collection<Order> prepareObjectList() {
        return orderController.findAllClosedOrders();
    }
}
