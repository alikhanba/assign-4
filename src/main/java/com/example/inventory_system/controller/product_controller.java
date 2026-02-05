package com.example.inventory_system.controller;


import com.example.inventory_system.model.Product;
import com.example.inventory_system.inventory.Inventory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 2. Tells Spring this class handles web requests
@RequestMapping("/api") // 3. All your URLs will start with http://localhost:8080/api
public class product_controller {

    // 4. Create an instance of your Inventory class to talk to the DB
    private Inventory inventory = new Inventory("Main Warehouse", 500);

    // 5. This handles: http://localhost:8080/api/products
    @GetMapping("/products")
    public List<Product> getAll() {
        // This calls the method you refactored in the Inventory class!
        return inventory.getProducts();
    }

    // 6. This handles searching: http://localhost:8080/api/products/search/Laptop
    @GetMapping("/products/search/{name}")
    public Product search(@PathVariable String name) {
        return inventory.getProductbyname(name);
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestBody Product newProduct) {
        inventory.addProduct(newProduct);
        return "Product added successfully!";
    }


}