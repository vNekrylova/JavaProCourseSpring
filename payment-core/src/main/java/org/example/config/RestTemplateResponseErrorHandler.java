package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dto.ErrorDto;
import org.example.exception.IntegrationException;
import org.example.exception.ResponseIntegrationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
            throw new IntegrationException("Произошла ошибка при вызове внешнего сервиса");
        }
        if (response.getStatusCode().is4xxClientError()) {
            ErrorDto responseErrorDto = objectMapper.readValue(response.getBody(), ErrorDto.class);
            throw new ResponseIntegrationException(
                    "Неверные данные или параметры запроса: " + responseErrorDto.message(),
                    responseErrorDto.code());
        }
    }
}
