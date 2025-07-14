package org.example.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class ClientProperties {

    private String uri;
    private Duration connectTimeout;
    private Duration readTimeout;
}
