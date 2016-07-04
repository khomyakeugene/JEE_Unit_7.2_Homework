package com.company.restaurant.application.data.adder.proto;

import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.service.DatabaseService;
import com.company.restaurant.application.data.service.Executor;
import com.company.util.Util;

import java.util.Collection;

/**
 * Created by Yevhen on 29.05.2016.
 */
public abstract class ObjectAdderProto<T> extends DatabaseService implements Executor {
    private static final String EXISTING_DATA = "Existing data:";

    private ObjectTableList<T> objectTableList;

    public void setObjectTableList(ObjectTableList<T> objectTableList) {
        this.objectTableList = objectTableList;
    }

    protected abstract T addObject();

    private Collection<T> addObjects() {
        Collection<T> result ;
        T object;

        do {
            existingDataMessage();
            result = objectTableList.displayObjectList(objectTableList.prepareObjectList());
            object = addObject();
        } while (object != null);

        return result;
    }

    protected String getExistingDataMessage() {
        return EXISTING_DATA;
    }

    private void existingDataMessage() {
        Util.printMessage(getExistingDataMessage());
    }


    @Override
    public void execute() {
        addObjects();
    }
}
