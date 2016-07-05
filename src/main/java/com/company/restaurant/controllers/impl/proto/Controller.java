package com.company.restaurant.controllers.impl.proto;

import com.company.util.DataIntegrityException;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class Controller {
    protected void throwDataIntegrityException(String message) {
        throw new DataIntegrityException(message);
    }
}
