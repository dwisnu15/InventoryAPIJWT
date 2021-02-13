package com.enigma.api.inventory.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
//doesnt have response because it doesnt have foreign key
public class UnitModel {

    private Integer id;

    @NotBlank
    @Size(min = 1, max = 20)
    private String code;


    @NotBlank
    @Size(min = 1, max = 100)
    private String description;

    public UnitModel() {
        //for instancing empty pagedlist
    }

    public UnitModel(Integer id, @NotBlank @Size(min = 1, max = 20) String code, @NotBlank @Size(min = 1, max = 100) String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
