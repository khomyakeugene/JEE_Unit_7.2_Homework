package com.company.restaurant.application.data.service;

import com.company.util.Util;

/**
 * Created by Yevhen on 29.05.2016.
 */
public abstract class DataFinderAndChooserProto<KeyFieldType> extends DatabaseService {
    private static final String ENTER_NAME_MESSAGE = "Please, enter name";
    private static final String ENTER_IDENTIFIER_MESSAGE = "Please, enter identifier";

    protected abstract KeyFieldType readKeyFieldValue();

    protected void objectDataHasNotBeenFoundMessage() {
        oneObjectHasNotBeenFoundMessage();
    }

    protected String getEnterIdentifierMessage() {
        return ENTER_IDENTIFIER_MESSAGE;
    }

    protected String getEnterNameMessage() {
        return ENTER_NAME_MESSAGE;
    }

    protected String readStringKeyFieldValue() {
        String result = Util.readInputString(getEnterNameMessage(), false);

        return (result == null) ? null : result.trim();
    }

    protected Integer readIntegerKeyFieldValue() {
        return Util.readInputInt(getEnterIdentifierMessage(), false);
    }
}
