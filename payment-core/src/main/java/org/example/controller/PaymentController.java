package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ExecuteDto;
import org.example.dto.ExecuteResponseDto;
import org.example.dto.ProductDto;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(value = "/{id}")
    public List<ProductDto> findUserProducts(@PathVariable Long id) {
        return paymentService.getUserProducts(id);
    }

    @PatchMapping(value = "/execute")
    public ExecuteResponseDto paymentExecute(@RequestBody ExecuteDto executeDto) {
        return paymentService.paymentExecute(executeDto);
    }
}
