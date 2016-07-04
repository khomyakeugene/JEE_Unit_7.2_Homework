package com.company.restaurant.application.data.remover.proto;

import com.company.restaurant.application.data.service.ObjectProcessorProto;

/**
 * Created by Yevhen on 02.06.2016.
 */
public abstract class ObjectRemoverProto<T> extends ObjectProcessorProto<T> {
    protected abstract void deleteObject(T object);

    @Override
    protected String getActionHasBeenSuccessfullyPerformedMessage(T object) {
        return DATA_HAS_BEEN_SUCCESSFULLY_DELETED;
    }

    @Override
    protected String processObject(T object) {
        deleteObject(object);

        return null;
    }
}
