package com.company.restaurant.application.data.list;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.list.proto.ObjectTableListProto;
import com.company.restaurant.controllers.TableController;
import com.company.restaurant.model.Table;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public class TableTableList extends ObjectTableListProto<Table> implements ObjectTableList<Table> {
    private static final String[] listHeader = new String[] {
            "Table id",
            "Table number"
    };

    private TableController tableController;

    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }

    @Override
    public Collection<Table> prepareObjectList() {
        return tableController.findAllTables();
    }

    @Override
    protected String[] getListHeader() {
        return listHeader;
    }

    @Override
    protected String[] dataSetRowDataToStringArray(Table table) {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Integer.toString(table.getTableId()));
        arrayList.add(Integer.toString(table.getNumber()));

        return arrayList.toArray(new String[arrayList.size()]);
    }
}
