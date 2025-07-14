package org.example.config;

import org.example.config.properties.ClientProperties;
import org.example.config.properties.RestTemplateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(RestTemplateProperties.class)
public class IntegrationConfiguration {

    @Bean
    public RestTemplate productsClient(RestTemplateProperties properties, RestTemplateResponseErrorHandler errorHandler) {

        ClientProperties productsClientProperties = properties.getClients().get("products-client");
        return new RestTemplateBuilder()
                .rootUri(productsClientProperties.getUri())
                .connectTimeout(productsClientProperties.getConnectTimeout())
                .errorHandler(errorHandler)
                .readTimeout(productsClientProperties.getReadTimeout())
                .build();
    }
}
