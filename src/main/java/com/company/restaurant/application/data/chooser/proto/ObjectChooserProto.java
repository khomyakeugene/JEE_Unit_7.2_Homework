package com.company.restaurant.application.data.chooser.proto;

import com.company.restaurant.application.data.service.ObjectFinderAndChooserProto;

import java.util.Collection;

/**
 * Created by Yevhen on 27.05.2016.
 */
public abstract class ObjectChooserProto<ObjectType, ObjectKeyFieldType>
        extends ObjectFinderAndChooserProto<ObjectType, ObjectKeyFieldType>
        implements ObjectChooser<ObjectType> {

    protected abstract ObjectType findObject(ObjectKeyFieldType objectKeyFieldValue);

    private ObjectType chooseObjectFromList(Collection<ObjectType> objects) {
        ObjectType result = null;

        ObjectKeyFieldType objectKeyFieldValue = null;
        do {
            Collection<ObjectType> list = objectTableList.displayObjectList(objects);
            if (list != null && list.size() > 0) {
                objectKeyFieldValue = readKeyFieldValue();
                if (objectKeyFieldValue != null) {
                    result = findObject(objectKeyFieldValue);
                    if (result == null) {
                        objectDataHasNotBeenFoundMessage();
                    }
                }
            }
        } while (objectKeyFieldValue != null && result == null);

        return result;
    }

    @Override
    public Collection<ObjectType> displayObjectList() {
        return objectTableList.displayObjectList(objectTableList.prepareObjectList());
    }

    public ObjectType chooseObjectFromList() {
        return chooseObjectFromList(objectTableList.prepareObjectList());
    }
}
