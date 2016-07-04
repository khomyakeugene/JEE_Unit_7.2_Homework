package com.company.restaurant.application.data.adder;

import com.company.restaurant.application.data.adder.proto.ObjectAdderProto;
import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.application.data.collector.proto.ItemCollector;
import com.company.restaurant.controllers.OrderController;
import com.company.restaurant.model.Employee;
import com.company.restaurant.model.Order;
import com.company.restaurant.model.Table;
import com.company.util.Util;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class OrderAdder extends ObjectAdderProto<Order> {
    private static final String ENTER_ORDER_NUMBER_MESSAGE = "Please, enter order number";

    private OrderController orderController;
    private ObjectChooser<Employee> employeeChooser;
    private ObjectChooser<Table> tableChooser;
    private ItemCollector<Order> orderCourseCollector;

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setEmployeeChooser(ObjectChooser<Employee> employeeChooser) {
        this.employeeChooser = employeeChooser;
    }

    public void setTableChooser(ObjectChooser<Table> tableChooser) {
        this.tableChooser = tableChooser;
    }

    public void setOrderCourseCollector(ItemCollector<Order> orderCourseCollector) {
        this.orderCourseCollector = orderCourseCollector;
    }

    private Order addOrder() {
        Order result = null;

        String orderNumber = Util.readInputString(ENTER_ORDER_NUMBER_MESSAGE);
        if (orderNumber != null && !orderNumber.isEmpty()) {
            Employee employee = employeeChooser.chooseObjectFromList();
            if (employee != null) {
                Table table = tableChooser.chooseObjectFromList();
                if (table != null) {
                    Order order = new Order();
                    order.setOrderNumber(orderNumber.trim());
                    order.setWaiter(employee);
                    order.setTable(table);

                    result = orderController.addOrder(order);
                    dataHasBeenSuccessfullyAddedMessage();
                }
            }
        }

        return result;
    }


    @Override
    protected Order addObject() {
        Order result = addOrder();
        orderCourseCollector.addItemsToObject(result);

        return result;
    }
}
