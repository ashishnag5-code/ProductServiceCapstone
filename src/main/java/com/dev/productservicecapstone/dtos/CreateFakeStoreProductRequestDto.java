package com.dev.productservicecapstone.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFakeStoreProductRequestDto {
    private String description;
    private String imageUrl;
    private Double price;
    private String category;
    private String name;

}
