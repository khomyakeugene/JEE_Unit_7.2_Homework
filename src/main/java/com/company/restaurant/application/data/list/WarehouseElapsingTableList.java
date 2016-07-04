package com.company.restaurant.application.data.list;

import com.company.util.Util;

/**
 * Created by Yevhen on 02.06.2016.
 */
public class WarehouseElapsingTableList extends WarehouseTableList {
    private static final String ENTER_BOUNDARY_VALUE_MESSAGE = "Please, enter boundary value";

    @Override
    public void execute() {
        Float boundaryValue = Util.readInputPositiveFloat(ENTER_BOUNDARY_VALUE_MESSAGE, false);
        if (boundaryValue != null) {
            displayObjectList(
                    warehouseController.findAllElapsingWarehouseIngredients(boundaryValue));
        }
    }
}
