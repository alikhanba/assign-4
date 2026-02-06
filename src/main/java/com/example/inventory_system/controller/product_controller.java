package com.example.inventory_system.controller;


import com.example.inventory_system.model.Product;
import com.example.inventory_system.inventory.Inventory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class product_controller {

    private Inventory inventory = new Inventory("Main Warehouse", 500);

    @GetMapping("/products")
    public List<Product> getAll() {
        return inventory.getProducts();
    }

    @GetMapping("/products/search/{name}")
    public Product search(@PathVariable String name) {
        return inventory.getProductbyname(name);
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestBody Product newProduct) {
        inventory.addProduct(newProduct);
        return "Product added successfully!";
    }

    @DeleteMapping("/products/delete/{name}")
    public String deleteProduct(@PathVariable String name) {
        boolean deleted = inventory.deleteProduct(name);
        if (deleted) {
            return "Product deleted successfully!";
        }else{
            return "Product not deleted successfully!";
        }
    }


}