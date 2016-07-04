package com.company.restaurant.controllers;

import org.junit.BeforeClass;

/**
 * Created by Yevhen on 08.06.2016.
 */
public class RestaurantControllerHibernateTest extends RestaurantControllerTest {
    private final static String APPLICATION_CONTEXT_NAME = "restaurant-controller-context.xml";

    @BeforeClass
    public static void setUpClass() throws Exception {
        initDataSource(APPLICATION_CONTEXT_NAME);

        initEnvironment();
    }
}
