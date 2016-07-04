package com.company.restaurant.model;

import com.company.restaurant.model.proto.SimpleDic;

/**
 * Created by Yevhen on 24.05.2016.
 */
public class Ingredient extends SimpleDic {
    public int getIngredientId() {
        return getId();
    }

    public void setIngredientId(int ingredientId) {
        setId(ingredientId);
    }
}
