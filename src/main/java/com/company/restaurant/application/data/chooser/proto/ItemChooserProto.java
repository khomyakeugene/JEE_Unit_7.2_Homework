package com.company.restaurant.application.data.chooser.proto;

import com.company.restaurant.application.data.list.proto.ItemTableList;
import com.company.restaurant.application.data.service.DataFinderAndChooserProto;

import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public abstract class ItemChooserProto<ObjectType, ItemType, ItemKeyFieldType>
        extends DataFinderAndChooserProto<ItemKeyFieldType>
        implements ItemChooser<ObjectType, ItemType> {

    private ItemTableList<ObjectType, ItemType> itemTableList;

    public void setItemTableList(ItemTableList<ObjectType, ItemType> itemTableList) {
        this.itemTableList = itemTableList;
    }

    protected abstract ItemType findItem(ObjectType object, ItemKeyFieldType itemKeyFieldValue);

    private ItemType chooseItemFromList(ObjectType object, Collection<ItemType> items) {
        ItemType result = null;

        ItemKeyFieldType itemKeyFieldValue = null;
        do {
            Collection<ItemType> collection = itemTableList.displayObjectList(items);
            if (collection != null && collection.size() > 0) {
                itemKeyFieldValue = readKeyFieldValue();
                if (itemKeyFieldValue != null) {
                    result = findItem(object, itemKeyFieldValue);
                    if (result == null) {
                        objectDataHasNotBeenFoundMessage();
                    }
                }
            }
        } while (itemKeyFieldValue != null && result == null);

        return result;
    }

    @Override
    public Collection<ItemType> displayItemList(ObjectType object) {
        return itemTableList.displayItemList(object);
    }

    @Override
    public ItemType chooseItemFromList(ObjectType object) {
        return chooseItemFromList(object, itemTableList.prepareItemList(object));
    }
}
