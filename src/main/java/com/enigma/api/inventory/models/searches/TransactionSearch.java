
package com.enigma.api.inventory.models.searches;

import com.enigma.api.inventory.models.PageSearch;

import lombok.Setter;
import lombok.Getter;

public class TransactionSearch extends PageSearch {

    private Integer customerId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}