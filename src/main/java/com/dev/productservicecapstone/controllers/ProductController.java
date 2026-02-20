package com.dev.productservicecapstone.controllers;
import com.dev.productservicecapstone.dtos.CreateFakeStoreProductRequestDto;
import com.dev.productservicecapstone.dtos.ProductResponseDto;
import com.dev.productservicecapstone.exceptions.ProductNotFoundException;
import com.dev.productservicecapstone.models.Product;
import com.dev.productservicecapstone.services.ProductService;
import com.dev.productservicecapstone.dtos.ErrorDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody CreateFakeStoreProductRequestDto
                    createFakeStoreProductRequestDto)
    {
        Product product = productService.createProduct(
                createFakeStoreProductRequestDto.getName(),
                createFakeStoreProductRequestDto.getDescription(),
                createFakeStoreProductRequestDto.getPrice(),
                createFakeStoreProductRequestDto.getImageUrl(),
                createFakeStoreProductRequestDto.getCategory()
        );
        return new ResponseEntity<>(ProductResponseDto.from(product), HttpStatus.CREATED);
    }


    @PutMapping("/products/{id}")
    public ProductResponseDto replaceProduct(@PathVariable("id") long id,
                                             @RequestBody CreateFakeStoreProductRequestDto
                                                     createFakeStoreProductRequestDto)
    {
        Product product = productService.replaceProduct(
                id,
                createFakeStoreProductRequestDto.getName(),
                createFakeStoreProductRequestDto.getDescription(),
                createFakeStoreProductRequestDto.getPrice(),
                createFakeStoreProductRequestDto.getImageUrl(),
                createFakeStoreProductRequestDto.getCategory()
        );

        return ProductResponseDto.from(product);
    }

}
