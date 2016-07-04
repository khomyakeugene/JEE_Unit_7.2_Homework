package com.company.restaurant.application.data.remover;

import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.application.data.list.proto.ObjectTableList;
import com.company.restaurant.application.data.remover.proto.ObjectRemoverProto;
import com.company.restaurant.controllers.WarehouseController;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.Warehouse;
import com.company.util.Util;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class WarehouseRemover extends ObjectRemoverProto<Warehouse> {
    private static final String ENTER_INGREDIENT_AMOUNT_MESSAGE = "Please, enter ingredient amount";

    private WarehouseController warehouseController;
    private ObjectTableList<Warehouse> warehouseTableList;
    private ObjectChooser<Portion> portionChooser;

    public void setWarehouseController(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
    }

    public void setWarehouseTableList(ObjectTableList<Warehouse> warehouseTableList) {
        this.warehouseTableList = warehouseTableList;
    }

    public void setPortionChooser(ObjectChooser<Portion> portionChooser) {
        this.portionChooser = portionChooser;
    }

    @Override
    protected void deleteObject(Warehouse warehouseView) {
        warehouseController.takeIngredientFromWarehouse(
                warehouseController.findIngredientById(warehouseView.getIngredient().getIngredientId()),
                warehouseController.findPortionById(warehouseView.getPortion().getPortionId()),
                warehouseView.getAmount());
    }

    @Override
    protected Warehouse chooseObjectFromList() {
        Warehouse result = super.chooseObjectFromList();
        if (result != null) {
            // Show only this ingredient
            warehouseTableList.displayObjectList(
                    warehouseController.findIngredientInWarehouseById(result.getIngredient().getIngredientId()));
            // Get potion id
            Portion portion = portionChooser.chooseObjectFromList();
            if (portion != null) {
                result.getPortion().setPortionId(portion.getPortionId());

                Float amount = Util.readInputPositiveFloat(ENTER_INGREDIENT_AMOUNT_MESSAGE, false);
                if (amount != null) {
                    result.setAmount(amount);

                    return result;
                }
            }
        }

        return null;
    }
}
