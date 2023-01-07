package com.hadi.ecommerce.service;

import com.hadi.ecommerce.entity.Category;
import com.hadi.ecommerce.exception.ResourceNotFoundException;
import com.hadi.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("category id " + id + " not found."));
    }

    public Category create(Category category) {
        category.setId(UUID.randomUUID().toString());
        return categoryRepository.save(category);
    }

    public Category edit(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

}
