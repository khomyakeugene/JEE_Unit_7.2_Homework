package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ItemTableList;
import com.company.restaurant.application.data.list.proto.ItemTableListProto;
import com.company.restaurant.controllers.OrderController;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;

import java.util.Collection;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class OrderCourseTableList extends ItemTableListProto<Order, Course>
        implements ItemTableList<Order, Course> {
    private static final String ORDER_IS_EMPTY_MESSAGE = "Order is empty";

    private OrderController orderController;

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    @Override
    public Collection<Course> prepareItemList(Order order) {
        return orderController.findOrderCourses(order);
    }

    @Override
    protected String[] getListHeader() {
        return CourseTableList.listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Course course) {
        return CourseTableList.getCourseRowData(course);
    }

    @Override
    protected void listDataHasNotBeenFoundMessage() {
        errorMessage(ORDER_IS_EMPTY_MESSAGE);
    }
}
