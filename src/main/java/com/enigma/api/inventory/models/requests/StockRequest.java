package com.enigma.api.inventory.models.requests;


import javax.validation.constraints.NotNull;


public class StockRequest {

    private Integer quantity;

    @NotNull
    private Integer itemId;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
