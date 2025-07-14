package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.ExecuteDto;
import org.example.dto.ExecuteResponseDto;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidOperationException;
import org.example.mapper.ProductMapper;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Transactional
    public ExecuteResponseDto updateProduct(ExecuteDto executeDto) {

        Product product = productRepository.findProductByAccountNumber(executeDto.accountNumber())
                .orElseThrow(EntityNotFoundException::new);

        switch (executeDto.operation()) {
            case "deposit":
                product.setBalance(product.getBalance().add(executeDto.amount()));
                break;
            case "withdraw":
                if (product.getBalance().compareTo(executeDto.amount()) < 0) {
                    throw new InsufficientFundsException("Недостаточно средств");
                }
                product.setBalance(product.getBalance().subtract(executeDto.amount()));
                break;
            default: throw new InvalidOperationException("Неверная операция");
        }

        productRepository.save(product);

        return new ExecuteResponseDto(executeDto.accountNumber(), LocalDateTime.now(), "Операция выполнена успешно");
    }
}
