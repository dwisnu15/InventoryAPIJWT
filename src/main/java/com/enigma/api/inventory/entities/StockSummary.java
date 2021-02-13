package com.enigma.api.inventory.entities;

public class StockSummary {

    private Item item;
    //if the type is integer, change the field into long (change the type into its super type)
    private Integer totalQuantity;

    public StockSummary() {
        //for empty stock summary
    }

    public StockSummary(Item item, Integer totalQuantity) {
        this.item = item;
        this.totalQuantity = totalQuantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
