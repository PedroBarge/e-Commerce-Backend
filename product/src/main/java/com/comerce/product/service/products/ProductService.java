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
    private final ProductBuilders productBuilders;

    public List<ProductDtoResponse> getAllProducts() {
        List<ProductEntity> productEntityList = repository.findAll();
        return productEntityList.stream()
                .filter(ProductEntity::isAvailable)
                .map(productBuilders::builderProductDtoResponse)
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
        return productBuilders.builderProductDtoResponse(newProduct);

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

        return product.map(productBuilders::builderProductDtoResponse).orElse(null);

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
            return productBuilders.builderProductDtoResponse(updatedProduct);
        }
        return null;
    }

    private static boolean checkIfIsAvailable(Optional<ProductEntity> productEntity) {
        return !productEntity.get().isAvailable();
    }

}
