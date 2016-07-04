package com.company.restaurant.application.data.chooser.proto;

import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public interface ItemChooser<ObjectType, ItemType> {
    Collection<ItemType> displayItemList(ObjectType object);

    ItemType chooseItemFromList(ObjectType object);
}
