package com.comerce.product.service.products;

import com.comerce.product.dto.products.ProductDtoRequest;
import com.comerce.product.dto.products.ProductDtoResponse;
import com.comerce.product.entity.products.ProductEntity;
import com.comerce.product.repository.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductBuilders productBuilders;

    public List<ProductDtoResponse> getAllProducts() {
        List<ProductEntity> productEntityList = repository.findAll();
        return productEntityList.stream()
                .filter(ProductEntity::isAvailable)
                .map(productBuilders::getProductDtoResponse)
                .sorted(Comparator.comparing(ProductDtoResponse::getCreatedAt).reversed())
                .toList();
    }

    public ProductDtoResponse createNewProduct(ProductDtoRequest productDto) {
        if (productDto.getLinkPhoto() == null ||
                productDto.getName() == null ||
                productDto.getDescription() == null ||
                productDto.getPrice() == null) {
            return null;
        }

        ProductEntity newProduct = repository.save(productBuilders.getProductEntity(productDto));

        return productBuilders.getProductDtoResponse(newProduct);

    }
    private static final String UPLOAD_DIR = Paths.get(System.getProperty("user.dir"), "uploads").toString();;
    public String uploadImage(MultipartFile file) {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            if (uploadDir.mkdirs()) {
                System.out.println("Diretório criado: " + uploadDir.getAbsolutePath());
            } else {
                System.out.println("Falha ao criar diretório: " + uploadDir.getAbsolutePath());
            }
        } else {
            System.out.println("Diretório já existe: " + uploadDir.getAbsolutePath());
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        try {
            file.transferTo(filePath.toFile());
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload da imagem: ", e);
        }
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

        return product.map(productBuilders::getProductDtoResponse).orElse(null);

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
            return productBuilders.getProductDtoResponse(updatedProduct);
        }
        return null;
    }

    private static boolean checkIfIsAvailable(Optional<ProductEntity> productEntity) {
        return !productEntity.get().isAvailable();
    }

}
