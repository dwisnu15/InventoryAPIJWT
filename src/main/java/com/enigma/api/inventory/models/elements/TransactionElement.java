package com.enigma.api.inventory.models.elements;

import com.enigma.api.inventory.models.CustomerModel;

public class TransactionElement {
    
    private Integer id;
    private CustomerModel customer;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
