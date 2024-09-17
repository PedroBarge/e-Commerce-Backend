package com.comerce.product.dto.products;

import com.comerce.product.entity.products.Category;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoRequest {
    private String linkPhoto;
    private String name;
    private String description;
    private String price;
    private Category category;
}
