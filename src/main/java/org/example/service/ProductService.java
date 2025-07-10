package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.example.mapper.ProductMapper;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new ProductDto(product.getAccountNumber(), product.getBalance(), product.getProductType());
    }

    public List<ProductDto> getProductsByUserId(Long userId) {
        return productRepository.findAllByUserId(userId)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
}
