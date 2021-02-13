package com.enigma.api.inventory.models.searches;

import com.enigma.api.inventory.models.PageSearch;
import lombok.Getter;
import lombok.Setter;

public class StockSearch extends PageSearch {

    private Integer itemId;
    private Integer quantity;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
