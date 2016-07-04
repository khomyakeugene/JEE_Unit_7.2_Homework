package com.company.restaurant.application.service;

import com.company.restaurant.application.data.service.Executor;
import com.company.util.DataIntegrityException;
import com.company.util.Util;

/**
 * Created by Yevhen on 27.05.2016.
 */
public class SafeMenuItem extends SimpleMenuItem implements MenuItem {
    private Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    private void performAction() {
        executor.execute();
    }

    @Override
    public boolean menuAction() {
        try {
            performAction();
        } catch (DataIntegrityException e) {
            Util.printMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
