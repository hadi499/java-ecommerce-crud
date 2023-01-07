package com.hadi.ecommerce.service;

import com.hadi.ecommerce.entity.Product;
import com.hadi.ecommerce.exception.BadRequestException;
import com.hadi.ecommerce.exception.ResourceNotFoundException;
import com.hadi.ecommerce.repository.CategoryRepository;
import com.hadi.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product id = " + id + " not found."));
    }

    public Product create(Product product) {
        if(!StringUtils.hasText(product.getName())) {
            throw new BadRequestException("Name Product can not empty.");
        }
        if(product.getCategory() == null) {
            throw new BadRequestException("category can not be empty.");
        }
        if(!StringUtils.hasText(product.getCategory().getId())) {
            throw new BadRequestException("category id can not empty");
        }

        categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Category id: " + product.getCategory().getId() + " not found"
                ));

        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    public Product edit(Product product) {
        if(!StringUtils.hasText(product.getName())) {
            throw new BadRequestException("Name Product can not empty.");
        }
        if(product.getCategory() == null) {
            throw new BadRequestException("category can not be empty.");
        }
        if(!StringUtils.hasText(product.getCategory().getId())) {
            throw new BadRequestException("category id can not empty");
        }

        categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Category id: " + product.getCategory().getId() + " not found"
                ));

        return productRepository.save(product);
    }

    public Product changeImage(String id, String image) {
        Product product = findById(id);
        product.setImage(image);
        return productRepository.save(product);

    }


    public void delete(String id) {
        productRepository.deleteById(id);
    }

}
