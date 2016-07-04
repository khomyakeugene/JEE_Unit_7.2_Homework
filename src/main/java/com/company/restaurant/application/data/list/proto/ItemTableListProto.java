package com.company.restaurant.application.data.list.proto;

import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public abstract class ItemTableListProto<ObjectType, ItemType> extends ObjectTableListProto<ItemType>
        implements ItemTableList<ObjectType, ItemType> {
    @Override
    public Collection<ItemType> displayItemList(ObjectType object) {
        return displayObjectList(prepareItemList(object));
    }

    @Override
    public Collection<ItemType> prepareObjectList() {
        return null;
    }
}
