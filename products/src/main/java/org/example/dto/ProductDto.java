package org.example.dto;

import java.math.BigDecimal;

public record ProductDto(String accountNumber, BigDecimal balance, String productType) {
}
