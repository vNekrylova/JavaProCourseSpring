package org.example.dto;

import java.math.BigDecimal;

public record ExecuteDto(String operation, BigDecimal amount, String accountNumber) {
}
