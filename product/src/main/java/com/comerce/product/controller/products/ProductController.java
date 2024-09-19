package com.comerce.product.controller.products;

import com.comerce.product.dto.products.ProductDtoRequest;
import com.comerce.product.dto.products.ProductDtoResponse;
import com.comerce.product.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private static final String UPLOAD_DIR = Paths.get(System.getProperty("user.dir"), "uploads").toString();;

    @GetMapping
    public List<ProductDtoResponse> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDtoResponse getOneProduct(@PathVariable String id) {
        return service.getOneProductById(id);
    }

    @PostMapping
    public ResponseEntity<ProductDtoResponse> addNewProduct(@RequestPart("product") ProductDtoRequest productDto,
                                                            @RequestPart("file") MultipartFile file) {

        String imageUrl = service.uploadImage(file);
        productDto.setLinkPhoto(imageUrl);
        ProductDtoResponse response = service.createNewProduct(productDto);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ProductDtoResponse updateProduct(@PathVariable String id, @RequestBody ProductDtoRequest productDto) {
        return service.updateProductById(id, productDto);
    }

    @PutMapping("delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        return service.deleteProductById(id);
    }


}
