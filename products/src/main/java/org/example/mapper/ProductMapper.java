package org.example.mapper;

import jakarta.persistence.Column;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        return new ProductDto(product.getAccountNumber(), product.getBalance(), product.getProductType());
    }
}
