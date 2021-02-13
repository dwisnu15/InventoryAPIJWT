package com.enigma.api.inventory.models;


import javax.validation.constraints.*;

public class ItemModel {

    private Integer id;

    @NotBlank
    @Size(min = 1, max = 20)
    private String name;

    private Double price;

    private UnitModel unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public UnitModel getUnit() {
        return unit;
    }

    public void setUnit(UnitModel unit) {
        this.unit = unit;
    }
}
