package com.example.services.beans.ingredient;

import java.io.Serializable;

public class StepIngredient implements Serializable {
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
