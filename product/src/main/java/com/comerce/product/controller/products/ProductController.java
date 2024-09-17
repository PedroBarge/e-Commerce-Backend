package com.comerce.product.controller.products;

import com.comerce.product.dto.products.ProductDtoRequest;
import com.comerce.product.dto.products.ProductDtoResponse;
import com.comerce.product.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<ProductDtoResponse> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDtoResponse getOneProduct(@PathVariable String id) {
        return service.getOneProductById(id);
    }

    @PostMapping
    public ProductDtoResponse addNewProduct(@RequestBody ProductDtoRequest productDto) {
        return service.createNewProduct(productDto);
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
