package com.comerce.product.service;

import com.comerce.product.dto.ProductDto;
import com.comerce.product.entity.ProductEntity;
import com.comerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public String createNewProduct(ProductDto productDto) {
        if (productDto.getLinkPhoto() == null ||
                productDto.getName() == null ||
                productDto.getDescription() == null ||
                productDto.getPrice() == null) {
            return "Fail";
        }
        repository.save(ProductEntity.builder()
                .linkPhoto(productDto.getLinkPhoto())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build());
        return "Success";

    }

    public String deleteProductById(String id) {
        Optional<ProductEntity> product = repository.findById(id);
        if (product.isPresent()) {
            repository.delete(product.get());
            return "Delete with Success";
        }
        return "Fail";
    }

    public ProductDto getOneProductById(String id) {
        Optional<ProductEntity> find = repository.findById(id);

        return find.map(productEntity -> ProductDto.builder()
                .id(productEntity.getId())
                .linkPhoto(productEntity.getLinkPhoto())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .build()).orElse(null);

    }
}
