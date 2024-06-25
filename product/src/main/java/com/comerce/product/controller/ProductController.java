package com.comerce.product.controller;

import com.comerce.product.dto.ProductDto;
import com.comerce.product.service.ProductService;
import lombok.Getter;
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
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getOneProduct(@PathVariable String id) {
        return service.getOneProductById(id);
    }

    @PostMapping
    public String addNewProduct(@RequestBody ProductDto productDto) {
        return service.createNewProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        return service.deleteProductById(id);
    }


}
