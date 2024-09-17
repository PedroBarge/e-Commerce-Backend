package com.comerce.product.dto.products;

import com.comerce.product.entity.products.Category;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {

    private String id;
    private String linkPhoto;
    private String name;
    private String description;
    private String price;
    private Boolean isAvailable;
    private Category category;
}
