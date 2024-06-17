package com.ecommerce.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
    private Integer id;
    private String image;
    private String name;
    private String description;
    private Double price;
    private Boolean hasStock = true;
}
