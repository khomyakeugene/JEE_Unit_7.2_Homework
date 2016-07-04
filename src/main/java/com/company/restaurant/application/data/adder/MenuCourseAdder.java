package com.company.restaurant.application.data.adder;

import com.company.restaurant.application.data.collector.MenuCourseCollector;
import com.company.restaurant.application.data.service.Executor;

/**
 * Created by Yevhen on 02.06.2016.
 */
public class MenuCourseAdder extends MenuCourseCollector implements Executor {
    @Override
    public void execute() {
        addItemsToObject();
    }
}
