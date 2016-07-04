package com.company.restaurant.application.data.list.proto;

import java.util.Collection;

/**
 * Created by Yevhen on 28.05.2016.
 */
public interface ObjectTableList<T> {
    Collection<T> prepareObjectList();

    Collection<T> displayObjectList(Collection<T> objects);
}
