package org.example.dto;

import java.time.LocalDateTime;

public record ExecuteResponseDto(String accountNumber, LocalDateTime currentDate, String message) {
}
