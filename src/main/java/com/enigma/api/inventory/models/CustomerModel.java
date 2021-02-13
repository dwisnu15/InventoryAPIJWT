package com.enigma.api.inventory.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CustomerModel {
    
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 30)
    private String customerName;

    @Size(max = 40)
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
