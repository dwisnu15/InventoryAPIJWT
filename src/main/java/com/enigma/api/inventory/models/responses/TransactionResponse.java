package com.enigma.api.inventory.models.responses;

import com.enigma.api.inventory.models.CustomerModel;

public class TransactionResponse {
    
    private Integer id;

    private Integer itemQuantity;

    private Double totalPrice;

    private CustomerModel customer;

    private StockResponse stock;

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public StockResponse getStock() {
        return stock;
    }

    public void setStock(StockResponse stock) {
        this.stock = stock;
    }
}
