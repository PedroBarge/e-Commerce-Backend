package com.comerce.product.service.products;

import com.comerce.product.dto.products.ProductDtoRequest;
import com.comerce.product.dto.products.ProductDtoResponse;
import com.comerce.product.entity.products.ProductEntity;
import com.comerce.product.repository.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<ProductDtoResponse> getAllProducts() {
        List<ProductEntity> productEntityList = repository.findAll();
        return productEntityList.stream()
                .filter(ProductEntity::isAvailable)
                .map(productEntity -> ProductDtoResponse.builder()
                        .id(productEntity.getId())
                        .linkPhoto(productEntity.getLinkPhoto())
                        .name(productEntity.getName())
                        .description(productEntity.getDescription())
                        .price(productEntity.getPrice())
                        .isAvailable(productEntity.isAvailable())
                        .build())
                .toList();
    }

    public ProductDtoResponse createNewProduct(ProductDtoRequest productDto) {
        if (productDto.getLinkPhoto() == null ||
                productDto.getName() == null ||
                productDto.getDescription() == null ||
                productDto.getPrice() == null) {
            return null;
        }
        ProductEntity newProduct = repository.save(ProductEntity.builder()
                .linkPhoto(productDto.getLinkPhoto())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .isAvailable(true)
                .build());
        return new ProductDtoResponse(
                newProduct.getId(),
                newProduct.getLinkPhoto(),
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getPrice(),
                newProduct.isAvailable()
        );

    }

    public String deleteProductById(String id) {
        Optional<ProductEntity> product = repository.findById(id);
        if (checkIfIsAvailable(product)) return null;

        if (product.isPresent()) {
            product.get().setAvailable(false);
            repository.save(product.get());
            return "Delete with Success";
        }
        return "Fail";
    }

    public ProductDtoResponse getOneProductById(String id) {
        Optional<ProductEntity> product = repository.findById(id);
        if (checkIfIsAvailable(product)) return null;

        return product.map(productEntity -> ProductDtoResponse.builder()
                .id(productEntity.getId())
                .linkPhoto(productEntity.getLinkPhoto())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .isAvailable(productEntity.isAvailable())
                .build()).orElse(null);

    }

    public ProductDtoResponse updateProductById(String id, ProductDtoRequest productDto) {
        Optional<ProductEntity> product = repository.findById(id);
        if (checkIfIsAvailable(product)) return null;

        if (product.isPresent()) {
            var infoOldProd = product.get();
            infoOldProd.setLinkPhoto(productDto.getLinkPhoto());
            infoOldProd.setName(productDto.getName());
            infoOldProd.setDescription(productDto.getDescription());
            infoOldProd.setPrice(productDto.getPrice());

            ProductEntity updatedProduct = repository.save(infoOldProd);
            return new ProductDtoResponse(
                    updatedProduct.getId(),
                    updatedProduct.getLinkPhoto(),
                    updatedProduct.getName(),
                    updatedProduct.getDescription(),
                    updatedProduct.getPrice(),
                    updatedProduct.isAvailable()
            );
        }
        return null;
    }

    private static boolean checkIfIsAvailable(Optional<ProductEntity> productEntity) {
        if (!productEntity.get().isAvailable()) {
            return true;
        }
        return false;
    }
}
