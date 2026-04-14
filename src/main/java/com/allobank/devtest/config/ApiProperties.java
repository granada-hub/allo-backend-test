package com.allobank.devtest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "frankfurter")
@Data
public class ApiProperties {
    private String baseUrl;
}