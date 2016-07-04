package com.company.restaurant.application.data.chooser;

import com.company.restaurant.application.data.chooser.proto.ItemChooser;
import com.company.restaurant.application.data.chooser.proto.ItemChooserProto;
import com.company.restaurant.controllers.OrderController;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class OrderCourseChooser extends ItemChooserProto<Order, Course, Integer>
        implements ItemChooser<Order, Course> {
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter course identifier";

    private OrderController orderController;

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    @Override
    protected Integer readKeyFieldValue() {
        return readIntegerKeyFieldValue();
    }

    @Override
    protected String getEnterIdentifierMessage() {
        return ENTER_IDENTIFIER_MESSAGE;
    }

    @Override
    protected Course findItem(Order order, Integer courseId) {
        return orderController.findOrderCourseByCourseId(order, courseId);
    }

}
