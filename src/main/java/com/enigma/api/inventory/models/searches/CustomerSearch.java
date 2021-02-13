package com.enigma.api.inventory.models.searches;

import com.enigma.api.inventory.models.PageSearch;
import com.enigma.api.inventory.validations.Alphabetic;


public class CustomerSearch extends PageSearch {
    
    @Alphabetic
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
