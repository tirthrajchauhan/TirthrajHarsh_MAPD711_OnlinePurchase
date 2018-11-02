package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase.model;

import java.io.Serializable;

public class Shoes implements Serializable {
    private Long itemId;
    private String itemName;
    private String category;
    private Double shoeSize;
    private Double price;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(Double shoeSize) {
        this.shoeSize = shoeSize;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return itemName + " - $" + price;
    }
}
