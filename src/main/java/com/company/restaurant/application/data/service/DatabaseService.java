package com.company.restaurant.application.data.service;

import com.company.util.Util;

/**
 * Created by Yevhen on 27.05.2016.
 */
public class DatabaseService {
    private static final String DATA_HAS_BEEN_SUCCESSFULLY_ADDED = "Data has been successfully added";
    private static final String DATA_HAS_NOT_BEEN_FOUND_MESSAGE = "Data has not been found";
    protected static final String DATA_HAS_BEEN_SUCCESSFULLY_DELETED = "Data has been successfully deleted";

    protected void dataHasBeenSuccessfullyAddedMessage() {
        Util.printMessage(DATA_HAS_BEEN_SUCCESSFULLY_ADDED);
    }

    protected void dataHasBeenSuccessfullyDeletedMessage() {
        Util.printMessage(DATA_HAS_BEEN_SUCCESSFULLY_DELETED);
    }

    protected void errorMessage(String message) {
        Util.printMessage(message);
    }

    protected void listDataHasNotBeenFoundMessage() {
        errorMessage(DATA_HAS_NOT_BEEN_FOUND_MESSAGE);
    }

    protected void oneObjectHasNotBeenFoundMessage() {
        errorMessage(DATA_HAS_NOT_BEEN_FOUND_MESSAGE);
    }
}
