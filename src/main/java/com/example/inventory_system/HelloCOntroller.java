package com.example.inventory_system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCOntroller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, RESTful World!";
}}