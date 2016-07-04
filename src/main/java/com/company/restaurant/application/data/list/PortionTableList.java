package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.WarehouseController;
import com.company.restaurant.model.Portion;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class PortionTableList extends ObjectTableListProto<Portion> implements ObjectTableList<Portion> {
    private static final String[] listHeader = new String[] {
            "Portion Id",
            "Portion description"
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
    protected String[] dataSetRowDataToStringArray(Portion portion) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(portion.getPortionId()));
        arrayList.add(portion.getDescription());

        return arrayList.toArray(new String[arrayList.size()]);
    }

    @Override
    public Collection<Portion> prepareObjectList() {
        return warehouseController.findAllPortions();
    }
}
