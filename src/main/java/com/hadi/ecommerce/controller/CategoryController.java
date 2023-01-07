package com.hadi.ecommerce.controller;


import com.hadi.ecommerce.entity.Category;
import com.hadi.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category getAllById(@PathVariable("id") String id) {
        return categoryService.findById(id);
    }

    @PostMapping("/categories")
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/categories")
    public Category edit(@RequestBody Category category){
        return categoryService.edit(category);
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable("id") String id) {
        categoryService.delete(id);

    }

}
