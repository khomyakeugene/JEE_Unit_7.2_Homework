package com.company.restaurant.application.data.service;

import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.util.Util;

/**
 * Created by Yevhen on 28.05.2016.
 */
public abstract class ObjectProcessorProto<T> extends DatabaseService implements Executor {
    private static final String ACTION_HAS_BEEN_SUCCESSFULLY_PERFORMED = "The action has been successfully performed";

    private ObjectChooser<T> objectChooser;

    public void setObjectChooser(ObjectChooser<T> objectChooser) {
        this.objectChooser = objectChooser;
    }

    protected abstract String processObject(T object);

    protected T chooseObjectFromList() {
        return objectChooser.chooseObjectFromList();
    }

    private String processObjects() {
        String result = null;

        T object;
        do {
            object = chooseObjectFromList();
            if (object != null) {
                result = processObject(object);
                if (result  != null && !result.isEmpty()) {
                    errorMessage(result);
                } else {
                    actionHasBeenSuccessfullyPerformedMessage(object);
                }
            }
        } while (object != null);

        return result;
    }

    protected String getActionHasBeenSuccessfullyPerformedMessage(T object) {
        return ACTION_HAS_BEEN_SUCCESSFULLY_PERFORMED;
    }

    protected void actionHasBeenSuccessfullyPerformedMessage(T object) {
        String message = getActionHasBeenSuccessfullyPerformedMessage(object);

        if (message != null) {
            Util.printMessage(message);
        }
    }

    @Override
    public void execute() {
        processObjects();
    }
}
