package com.example.inventory_system.model;

public class Electronics extends Product {
    private String brand;
    public Electronics(String name, double price, int quantity, String description , String brand) {
        super(name, price, quantity,description);
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }
    @Override
    public String toString() {
        return super.toString()+ "[Brand: " + brand + "]";
    }
}
