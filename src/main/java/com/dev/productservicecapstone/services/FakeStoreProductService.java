package com.dev.productservicecapstone.services;
import com.dev.productservicecapstone.dtos.FakeStoreRequestDto;
import com.dev.productservicecapstone.dtos.FakeStoreResponseDto;
import com.dev.productservicecapstone.exceptions.ProductNotFoundException;
import com.dev.productservicecapstone.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

        FakeStoreResponseDto fakeStoreResponseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id, FakeStoreResponseDto.class);

        if (fakeStoreResponseDto == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }

        return fakeStoreResponseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts(){
        FakeStoreResponseDto[] fakeStoreResponseDto =  restTemplate.getForObject("http://fakestoreapi.com/products", FakeStoreResponseDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreResponseDto fakeStoreResponseDto1 : fakeStoreResponseDto) {
            Product product = new Product();
            products.add(product);
        }
        return products;

    }

    @Override
    public Product createProduct(String name, String description, double price,String imageUrl, String category){
        FakeStoreRequestDto fakeStoreRequestDto = createDtoFromParams(name, description, price, imageUrl, category);
        FakeStoreResponseDto fakeStoreResponseDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products", fakeStoreRequestDto, FakeStoreResponseDto.class
        );
        return fakeStoreResponseDto.toProduct();

    }

    private FakeStoreRequestDto createDtoFromParams(String name, String description, double price, String imageUrl, String category)
    {
        FakeStoreRequestDto fakeStoreRequestDto = new FakeStoreRequestDto();
        fakeStoreRequestDto.setTitle(name);
        fakeStoreRequestDto.setDescription(description);
        fakeStoreRequestDto.setPrice(price);
        fakeStoreRequestDto.setImage(imageUrl);
        fakeStoreRequestDto.setCategory(category);
        return fakeStoreRequestDto;
    }

    @Override
    public Product replaceProduct(long id, String name, String description, double price, String imageUrl, String category)
    {
        FakeStoreRequestDto updatedFakeStoreRequestDto = createDtoFromParams(name, description, price, imageUrl, category);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FakeStoreRequestDto> requestEntity =
                new HttpEntity<>(updatedFakeStoreRequestDto, headers);

        ResponseEntity<FakeStoreResponseDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreResponseDto.class
        );

        return responseEntity.getBody().toProduct();

    }
}
