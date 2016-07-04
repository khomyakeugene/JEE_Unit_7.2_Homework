package com.company.restaurant.application.data.remover;

import com.company.restaurant.application.data.collector.OrderCourseCollector;
import com.company.restaurant.application.data.service.Executor;

/**
 * Created by Yevhen on 02.06.2016.
 */
public class OrderCourseRemover extends OrderCourseCollector implements Executor {
    @Override
    public void execute() {
        delItemsFromObject();
    }
}
