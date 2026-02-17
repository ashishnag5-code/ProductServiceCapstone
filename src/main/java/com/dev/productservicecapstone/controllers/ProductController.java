package com.dev.productservicecapstone.controllers;

import com.dev.productservicecapstone.dtos.ProductResponseDto;
import com.dev.productservicecapstone.models.Product;
import com.dev.productservicecapstone.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {


    ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") long id){

        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);

    }
}
