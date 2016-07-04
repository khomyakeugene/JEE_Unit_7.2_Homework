package com.company.restaurant.application.data.list.proto;

import java.util.Collection;

/**
 * Created by Yevhen on 29.05.2016.
 */
public interface ItemTableList<ObjectType, ItemType> extends ObjectTableList<ItemType> {
    Collection<ItemType> prepareItemList(ObjectType object);

    Collection<ItemType> displayItemList(ObjectType object);
}
