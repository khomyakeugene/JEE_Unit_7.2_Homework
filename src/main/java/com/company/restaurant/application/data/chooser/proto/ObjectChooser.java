package com.company.restaurant.application.data.chooser.proto;

import java.util.Collection;

/**
 * Created by Yevhen on 27.05.2016.
 */
public interface ObjectChooser<T> {
    Collection<T> displayObjectList();

    T chooseObjectFromList();
}
