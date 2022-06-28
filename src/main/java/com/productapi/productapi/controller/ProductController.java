package com.productapi.productapi.controller;

import com.productapi.productapi.entity.Product;
import com.productapi.productapi.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return productRepository.findById(id).get();
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product){
        Product productDataBase = productRepository.findById(id).get();
        BeanUtils.copyProperties(product, productDataBase, "id", "createdAt");
        return productRepository.save(productDataBase);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productRepository.deleteById(id);
    }
}
