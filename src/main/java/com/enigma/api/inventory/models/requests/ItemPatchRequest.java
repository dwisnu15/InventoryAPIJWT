package com.enigma.api.inventory.models.requests;

import com.enigma.api.inventory.validations.Alphabetic;

import javax.validation.constraints.Size;

public class ItemPatchRequest {

    @Alphabetic
    @Size(min = 1, max = 50)
    private String name;

    private Long price;

    private Integer unitId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }
}
