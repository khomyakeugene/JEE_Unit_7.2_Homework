package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.WarehouseController;
import com.company.restaurant.model.Warehouse;
import com.company.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import static com.company.util.Util.toStringMaskNullAsEmpty;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class WarehouseTableList extends ObjectTableListProto<Warehouse>
        implements ObjectTableList<Warehouse> {
    private static final String THERE_ARE_NO_INGREDIENTS_IN_WAREHOUSE_MESSAGE = "There are no ingredients in the warehouse";
    private static final String[] listHeader = new String[]{
            "Ingredient Id",
            "Ingredient name",
            "Amount",
            "Portion name"
    };

    protected WarehouseController warehouseController;

    public void setWarehouseController(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
    }

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Warehouse warehouse) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(warehouse.getIngredient().getIngredientId()));
        arrayList.add(warehouse.getIngredient().getName());
        arrayList.add(toStringMaskNullAsEmpty(warehouse.getAmount()));
        arrayList.add(warehouse.getPortion().getDescription());

        return arrayList.toArray(new String[arrayList.size()]);
    }

    @Override
    public Collection<Warehouse> prepareObjectList() {
        return warehouseController.findAllWarehouseIngredients();
    }

    @Override
    protected void listDataHasNotBeenFoundMessage() {
        Util.printMessage(THERE_ARE_NO_INGREDIENTS_IN_WAREHOUSE_MESSAGE);
    }
}
