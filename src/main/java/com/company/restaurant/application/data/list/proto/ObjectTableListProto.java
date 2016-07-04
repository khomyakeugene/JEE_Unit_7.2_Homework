package com.company.restaurant.application.data.list.proto;

import com.company.restaurant.application.data.service.DatabaseService;
import com.company.restaurant.application.data.service.Executor;
import com.company.util.AlignmentType;
import com.company.util.TableBuilder;
import com.company.util.Util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Yevhen on 27.05.2016.
 */
public abstract class ObjectTableListProto<T> extends DatabaseService
        implements ObjectTableList<T>, Executor {
    private static final String DATA_HAS_NOT_BEEN_FOUND_MESSAGE = "Data has not been found";

    protected abstract String[] getListHeader();

    protected abstract String[] dataSetRowDataToStringArray(T dataSetRow);

    protected void errorMessage(String message) {
        Util.printMessage(message);
    }

    protected void listDataHasNotBeenFoundMessage() {
        errorMessage(DATA_HAS_NOT_BEEN_FOUND_MESSAGE);
    }

    @Override
    public Collection<T> displayObjectList(Collection<T> objects) {
        if (objects != null && objects.size() > 0) {
            ArrayList<String[]> arrayList = new ArrayList<>();

            String[] listHeader = getListHeader();
            arrayList.add(listHeader);
            objects.forEach(e -> arrayList.add(dataSetRowDataToStringArray(e)));
            Util.printTable(TableBuilder.buildTable(arrayList.toArray(new String[arrayList.size()][listHeader.length]),
                    AlignmentType.LEFT, false));
        } else {
            listDataHasNotBeenFoundMessage();
        }

        return objects;
    }

    public Collection<T> displayObjectList() {
        return displayObjectList(prepareObjectList());
    }

    @Override
    public void execute() {
        displayObjectList();
    }
}
