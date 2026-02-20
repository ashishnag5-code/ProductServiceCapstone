package com.dev.productservicecapstone.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreRequestDto {
    private String description;
    private String image;
    private Double price;
    private String category;
    private String title;

}
