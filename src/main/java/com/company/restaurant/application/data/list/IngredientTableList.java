package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.WarehouseController;
import com.company.restaurant.model.Ingredient;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class IngredientTableList extends ObjectTableListProto<Ingredient> implements ObjectTableList<Ingredient> {
    private static final String[] listHeader = new String[] {
            "Ingredient Id",
            "Ingredient name"
    };

    private WarehouseController warehouseController;

    public void setWarehouseController(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
    }

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Ingredient ingredient) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(ingredient.getId()));
        arrayList.add(ingredient.getName());

        return arrayList.toArray(new String[arrayList.size()]);
    }

    @Override
    public Collection<Ingredient> prepareObjectList() {
        return warehouseController.findAllIngredients();
    }
}
