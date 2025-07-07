package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/")
    public List<ProductDto> findAll() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
