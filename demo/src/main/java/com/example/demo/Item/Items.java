package com.example.demo.Item;

import java.math.BigDecimal;


public class Items {

    private String itemName;
    private BigDecimal pricePerUnit;
    private int quantity;
    private String isItemImported;
    private String category;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIsItemImported() {
        return isItemImported;
    }

    public void setIsItemImported(String isItemImported) {
        this.isItemImported = isItemImported;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
