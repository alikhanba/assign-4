package com.example.inventory_system.model;


import java.util.Objects;
public class Product{
    private String name;
    private double price;
    private int quantity;
    private String description;
    public Product(String name, double price, int quantity,String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public String getdescription(){return description;}
    public void setdescription(String description){ this.description = description; }

    @Override
    public String toString(){
        return "Product{" + "name=" + name + ", price=" + price + ", quantity=" + quantity + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if(obj==null || getClass()!= obj.getClass()) return false;
        Product product = (Product) obj;
        return this.price == product.price && this.name.equals(product.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, price);
    }
}