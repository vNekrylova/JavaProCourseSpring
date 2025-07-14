package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/user/{id}")
    public List<ProductDto> findUserProducts(@PathVariable Long id) {
        return productService.getProductsByUserId(id);
    }

    @PatchMapping(value = "/payment/execute")
    public ExecuteResponseDto paymentExecute(@RequestBody ExecuteDto executeDto) {
        return productService.updateProduct(executeDto);
    }
}
