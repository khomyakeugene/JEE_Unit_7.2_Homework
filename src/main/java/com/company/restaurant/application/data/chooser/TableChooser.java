package com.company.restaurant.application.data.chooser;

import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.application.data.chooser.proto.ObjectChooserProto;
import com.company.restaurant.controllers.TableController;
import com.company.restaurant.model.Table;

/**
 * Created by Yevhen on 27.05.2016.
 */
public class TableChooser extends ObjectChooserProto<Table, Integer> implements ObjectChooser<Table> {
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter table id";

    private TableController tableController;

    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }

    @Override
    protected String getEnterIdentifierMessage() {
        return ENTER_IDENTIFIER_MESSAGE;
    }

    @Override
    protected Table findObject(Integer tableId) {
        return tableController.findTableById(tableId);
    }

    @Override
    protected Integer readKeyFieldValue() {
        return readIntegerKeyFieldValue();
    }

}
