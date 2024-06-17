package com.ecommerce.e_commerce.service;

import com.ecommerce.e_commerce.dto.ProductsDto;
import com.ecommerce.e_commerce.entity.ProductsEntity;
import com.ecommerce.e_commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductRepository productRepository;

    public List<ProductsDto> getAllProducts() {
        List<ProductsEntity> entityList = productRepository.findAll();
        return entityList.stream()
                .map(ProductsService::productDtoBuilder)
                .toList();
    }

    public ProductsDto addOneProduct(ProductsDto productsDto) {
        ProductsEntity productsEntity = ProductsEntity.builder()
                .image(productsDto.getImage())
                .name(productsDto.getName())
                .description(productsDto.getDescription())
                .price(productsDto.getPrice())
                .build();

        return productDtoBuilder(productRepository.save(productsEntity));
    }

    public String removeOneProduct(Integer id) {
        ProductsEntity findInBd = productRepository.findById(id).orElse(null);
        if (findInBd != null) {
            productRepository.delete(findInBd);
            return "Deleted with success";
        }
        return "It's impossible to delete this product";
    }

    public ProductsDto updateProduct(Integer id, ProductsDto newProduct) {

        ProductsEntity oldProduct = productRepository.findById(id).orElse(null);
        if (oldProduct != null) {
            oldProduct.setImage(newProduct.getImage());
            oldProduct.setName(newProduct.getName());
            oldProduct.setDescription(newProduct.getDescription());
            oldProduct.setPrice(newProduct.getPrice());

            productRepository.save(oldProduct);
            return productDtoBuilder(oldProduct);
        }
        return null;

    }

    public ProductsDto getOneProducts(Integer id) {
        ProductsEntity entity = productRepository.findById(id).orElse(null);
        if (entity != null) {
            return productDtoBuilder(entity);
        }
        return null;
    }

    private static ProductsDto productDtoBuilder(ProductsEntity oldProduct) {
        return ProductsDto.builder()
                .id(oldProduct.getId())
                .image(oldProduct.getImage())
                .name(oldProduct.getName())
                .description(oldProduct.getDescription())
                .price(oldProduct.getPrice())
                .hasStock(oldProduct.getHasStock())
                .build();
    }
}
