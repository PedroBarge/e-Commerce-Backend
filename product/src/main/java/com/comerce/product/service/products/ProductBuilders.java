package com.comerce.product.service.products;

import com.comerce.product.dto.products.ProductDtoRequest;
import com.comerce.product.dto.products.ProductDtoResponse;
import com.comerce.product.entity.products.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductBuilders {
    public ProductDtoResponse getProductDtoResponse(ProductEntity newProduct) {
        return ProductDtoResponse.builder()
                .id(newProduct.getId())
                .linkPhoto(newProduct.getLinkPhoto())
                .name(newProduct.getName())
                .description(newProduct.getDescription())
                .price(newProduct.getPrice())
                .isAvailable(newProduct.isAvailable())
                .build();
    }

    public ProductEntity getProductEntity(ProductDtoRequest productDto) {
        return ProductEntity.builder()
                .linkPhoto(productDto.getLinkPhoto())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .isAvailable(true)
                //.clientId(productDto.getClientId())
                .build();
    }
}
