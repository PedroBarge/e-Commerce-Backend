package com.ecommerce.e_commerce.controller;

import com.ecommerce.e_commerce.dto.ProductsDto;
import com.ecommerce.e_commerce.service.ProductsService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class StoreController {
    private final ProductsService service;

    @GetMapping
    public List<ProductsDto> getAllProductsToHomePage(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductsDto getOneProductsToHomePage(@PathVariable Integer id){
        return service.getOneProducts(id);
    }

    @PostMapping
    public ProductsDto addOneProduct (@RequestBody ProductsDto productsDto){
        return service.addOneProduct(productsDto);
    }

    @PatchMapping("/{id}")
    public ProductsDto updateProduct (@PathVariable Integer id ,@RequestBody ProductsDto newProduct){
        return service.updateProduct(id,newProduct);
    }

    @DeleteMapping("/{id}")
    public String removeProduct(@PathVariable Integer id){
        return service.removeOneProduct(id);
    }
}
