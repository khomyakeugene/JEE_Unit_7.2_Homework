package com.company.restaurant.application.data.service;

import com.company.restaurant.application.data.list.proto.ObjectTableList;

/**
 * Created by Yevhen on 28.05.2016.
 */
public abstract class ObjectFinderAndChooserProto<ObjectType, ObjectKeyFieldType>
        extends DataFinderAndChooserProto<ObjectKeyFieldType> {

    protected ObjectTableList<ObjectType> objectTableList;

    public void setObjectTableList(ObjectTableList<ObjectType> objectTableList) {
        this.objectTableList = objectTableList;
    }
}
