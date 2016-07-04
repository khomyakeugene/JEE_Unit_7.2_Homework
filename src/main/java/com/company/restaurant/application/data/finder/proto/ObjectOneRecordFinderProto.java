package com.company.restaurant.application.data.finder.proto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 28.05.2016.
 */
public abstract class ObjectOneRecordFinderProto<ObjectType, ObjectKeyFieldType>
        extends ObjectFinderProto<ObjectType, ObjectKeyFieldType> {

    protected abstract ObjectType findObject(ObjectKeyFieldType objectKeyFieldValue);

    @Override
    protected List<ObjectType> findObjects(ObjectKeyFieldType objectKeyFieldValue) {
        List<ObjectType> result = new ArrayList<>();

        ObjectType object = findObject(objectKeyFieldValue);
        if (object != null) {
            result.add(object);
        }

        return result;
    }
}
