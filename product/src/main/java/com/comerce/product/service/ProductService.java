package com.comerce.product.service;

import com.comerce.product.dto.ProductDto;
import com.comerce.product.entity.ProductEntity;
import com.comerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productEntityList = repository.findAll();
        return productEntityList.stream()
                .map(productEntity -> ProductDto.builder()
                        .id(productEntity.getId())
                        .linkPhoto(productEntity.getLinkPhoto())
                        .name(productEntity.getName())
                        .description(productEntity.getDescription())
                        .price(productEntity.getPrice())
                        .build())
                .toList();
    }
}
