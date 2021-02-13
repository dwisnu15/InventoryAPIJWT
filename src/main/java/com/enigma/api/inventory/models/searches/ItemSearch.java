package com.enigma.api.inventory.models.searches;

import com.enigma.api.inventory.models.PageSearch;
import com.enigma.api.inventory.validations.Alphabetic;

public class ItemSearch extends PageSearch {

    @Alphabetic
    private String name;

    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
