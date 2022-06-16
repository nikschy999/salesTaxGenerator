package com.example.demo.finalBilling;

import java.math.BigDecimal;
import java.util.List;


public class finalBilling {

    private List<BigDecimal> salesTaxComponent;
    private List<BigDecimal> costComponent;

    public List<BigDecimal> getSalesTaxComponent() {
        return salesTaxComponent;
    }

    public void setSalesTaxComponent(List<BigDecimal> salesTaxComponent) {
        this.salesTaxComponent = salesTaxComponent;
    }

    public List<BigDecimal> getCostComponent() {
        return costComponent;
    }

    public void setCostComponent(List<BigDecimal> costComponent) {
        this.costComponent = costComponent;
    }
}
