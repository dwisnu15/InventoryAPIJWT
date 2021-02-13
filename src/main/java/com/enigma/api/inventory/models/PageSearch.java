package com.enigma.api.inventory.models;

import org.springframework.data.domain.Sort;


import javax.validation.constraints.Max;


public class PageSearch {

    private Integer page = 0;

    @Max(100)
    private Integer size = 10;

    private Sort.Direction sort = Sort.Direction.ASC;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Sort.Direction getSort() {
        return sort;
    }

    public void setSort(Sort.Direction sort) {
        this.sort = sort;
    }
}
