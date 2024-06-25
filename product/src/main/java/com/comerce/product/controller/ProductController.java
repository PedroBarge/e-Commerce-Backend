package com.comerce.product.controller;

import com.comerce.product.dto.ProductDto;
import com.comerce.product.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return service.getAllProducts();
    }
}
