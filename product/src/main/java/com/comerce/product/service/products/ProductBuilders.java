package com.comerce.product.service.products;

import com.comerce.product.dto.products.ProductDtoResponse;
import com.comerce.product.entity.products.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductBuilders {
    public ProductDtoResponse builderProductDtoResponse(ProductEntity newProduct) {
        return ProductDtoResponse.builder()
                .id(newProduct.getId())
                .linkPhoto(newProduct.getLinkPhoto())
                .name(newProduct.getName())
                .description(newProduct.getDescription())
                .price(newProduct.getPrice())
                .isAvailable(newProduct.isAvailable())
                .build();
    }
}
