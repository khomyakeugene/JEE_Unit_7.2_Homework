package com.company.restaurant.application.data.chooser;

import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.application.data.chooser.proto.ObjectChooserProto;
import com.company.restaurant.controllers.WarehouseController;
import com.company.restaurant.model.Portion;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class PortionChooser extends ObjectChooserProto<Portion, Integer> implements ObjectChooser<Portion> {
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter portion identifier";

    private WarehouseController warehouseController;

    public void setWarehouseController(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
    }

    @Override
    protected Portion findObject(Integer portionId) {
        return warehouseController.findPortionById(portionId);
    }

    @Override
    protected Integer readKeyFieldValue() {
        return readIntegerKeyFieldValue();
    }

    @Override
    protected String getEnterIdentifierMessage() {
        return ENTER_IDENTIFIER_MESSAGE;
    }

}
