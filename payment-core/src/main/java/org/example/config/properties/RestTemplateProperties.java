package org.example.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "integration")
public class RestTemplateProperties {

    private Map<String, ClientProperties> clients;
}
