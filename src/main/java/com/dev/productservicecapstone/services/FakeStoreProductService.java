package com.dev.productservicecapstone.services;

import com.dev.productservicecapstone.dtos.FakeStoreResponseDto;
import com.dev.productservicecapstone.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {

        FakeStoreResponseDto fakeStoreResponseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id, FakeStoreResponseDto.class);

        return fakeStoreResponseDto.toProduct();
    }
}
