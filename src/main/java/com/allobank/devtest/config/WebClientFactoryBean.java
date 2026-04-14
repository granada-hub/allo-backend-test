package com.allobank.devtest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component("frankfurterWebClient")
@RequiredArgsConstructor
public class WebClientFactoryBean implements FactoryBean<WebClient> {

    private final ApiProperties properties;

    @Override
    public WebClient getObject() {
        return WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return WebClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}