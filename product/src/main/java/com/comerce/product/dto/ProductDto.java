package com.comerce.product.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;
    private String linkPhoto;
    private String name;
    private String description;
    private String price;
}
