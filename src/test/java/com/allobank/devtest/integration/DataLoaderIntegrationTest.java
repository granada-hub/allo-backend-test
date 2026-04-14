package com.allobank.devtest.integration;

import com.allobank.devtest.service.StrategyRegistry;
import com.allobank.devtest.strategy.IDRDataFetcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static com.allobank.devtest.constant.StrategyKeyConst.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataLoaderIntegrationTest {
    @Autowired
    private StrategyRegistry registry;

    @Test
    void shouldLoadDataOnStartup() {
        Map<String, Object> data = new HashMap<>();

        for (IDRDataFetcher strategy : registry.getAll()) {
            Object response = strategy.fetch();
            data.put(strategy.getType(), response);
        }

        assertThat(data).isNotNull();
        assertThat(data).isNotEmpty();
        assertThat(data).containsKeys(
                STRATEGY_HISTORICAL,
                STRATEGY_AVAILABLE_CURRENCY,
                STRATEGY_LATEST_RATES
        );
    }
}