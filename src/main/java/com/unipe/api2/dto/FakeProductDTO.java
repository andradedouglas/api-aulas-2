package com.unipe.api2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class FakeProductDTO {
    private int id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

}
