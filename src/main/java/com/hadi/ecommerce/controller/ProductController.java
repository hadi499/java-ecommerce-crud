package com.hadi.ecommerce.controller;

import com.hadi.ecommerce.entity.Product;
import com.hadi.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/products")
    public Product edit(@RequestBody Product product) {
        return productService.edit(product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") String id) {
        productService.delete(id);
    }
}
