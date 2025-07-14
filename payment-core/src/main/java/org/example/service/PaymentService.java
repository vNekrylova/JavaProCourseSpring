package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.ExecuteDto;
import org.example.dto.ExecuteResponseDto;
import org.example.dto.ProductDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RestTemplate productsClient;

    public List<ProductDto> getUserProducts(Long userId) {
        return productsClient.exchange(
                "/user/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDto>>() {},
                userId).getBody();
    }

    public ExecuteResponseDto paymentExecute(ExecuteDto executeDto) {
        return productsClient.patchForObject("/payment/execute", executeDto, ExecuteResponseDto.class);
    }
}
