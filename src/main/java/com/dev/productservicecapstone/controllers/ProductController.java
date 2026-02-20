package com.dev.productservicecapstone.controllers;

import com.dev.productservicecapstone.dtos.ProductResponseDto;
import com.dev.productservicecapstone.exceptions.ProductNotFoundException;
import com.dev.productservicecapstone.models.Product;
import com.dev.productservicecapstone.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {


    ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") long id) throws ProductNotFoundException {


        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);

    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto>productResponseDtos = new ArrayList<>();

        products.forEach(product -> productResponseDtos.add(ProductResponseDto.from(product)));
        return productResponseDtos;
    }

    @PostMapping("/products")
    public
}
