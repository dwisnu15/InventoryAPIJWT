package com.enigma.api.inventory.models.requests;

import javax.validation.constraints.NotNull;

public class TransactionRequest {
    
    @NotNull
    private Integer stockId;

    private Double quantities;

    private Integer customerId;

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Double getQuantities() {
        return quantities;
    }

    public void setQuantities(Double quantities) {
        this.quantities = quantities;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
