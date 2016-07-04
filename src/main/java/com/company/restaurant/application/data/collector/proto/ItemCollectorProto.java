package com.company.restaurant.application.data.collector.proto;

import com.company.restaurant.application.data.chooser.proto.ItemChooser;
import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.application.data.service.DatabaseService;

/**
 * Created by Yevhen on 27.05.2016.
 */
public  abstract class ItemCollectorProto<ObjectType, NewItemType, PresentItemType>
        extends DatabaseService implements ItemCollector<ObjectType> {
    private ObjectChooser<ObjectType> objectChooser;
    private ObjectChooser<NewItemType> newItemChooser;
    private ItemChooser<ObjectType, PresentItemType> presentItemChooser;

    public void setObjectChooser(ObjectChooser<ObjectType> objectChooser) {
        this.objectChooser = objectChooser;
    }

    public void setNewItemChooser(ObjectChooser<NewItemType> newItemChooser) {
        this.newItemChooser = newItemChooser;
    }

    public void setPresentItemChooser(ItemChooser<ObjectType, PresentItemType> presentItemChooser) {
        this.presentItemChooser = presentItemChooser;
    }

    protected abstract void addItemToObject(ObjectType object, NewItemType item);

    protected abstract void delItemFromObject(ObjectType object, PresentItemType item);

    public void addItemsToObject() {
        addItemsToObject(objectChooser.chooseObjectFromList());
    }

    @Override
    public ObjectType addItemsToObject(ObjectType object) {
        if (object != null) {
            NewItemType item;
            do {
                // Item list
                presentItemChooser.displayItemList(object);
                // Choose new Item
                item = newItemChooser.chooseObjectFromList();
                if (item != null) {
                    try {
                        addItemToObject(object, item);
                        dataHasBeenSuccessfullyAddedMessage();
                    } catch (Exception e) {
                        e.printStackTrace();
                        item = null;
                    }
                }
            } while (item != null);
        }

        return object;
    }

    public void delItemsFromObject() {
        delItemsFromObject(objectChooser.chooseObjectFromList());
    }

    @Override
    public ObjectType delItemsFromObject(ObjectType object) {
        if (object != null) {
            PresentItemType item;
            do {
                item = presentItemChooser.chooseItemFromList(object);
                if (item != null) {
                    try {
                        delItemFromObject(object, item);
                        dataHasBeenSuccessfullyDeletedMessage();
                    } catch (Exception e) {
                        e.printStackTrace();
                        item = null;
                    }
                }
            } while (item != null);
        }

        return object;
    }
}
