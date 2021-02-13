package com.enigma.api.inventory.models.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CustomerRequest {

    @NotBlank
    @Size(min = 1, max = 30)
    private String customerName;

    @Size(max = 40)
    private String address;

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
