package com.dev.productservicecapstone.services;

import com.dev.productservicecapstone.exceptions.ProductNotFoundException;
import com.dev.productservicecapstone.models.Product;
import com.dev.productservicecapstone.dtos.FakeStoreRequestDto;
import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(String name, String description, double price,String imageUrl, String category);
    Product replaceProduct(long id, String name, String description, double price, String imageUrl, String category);


}
