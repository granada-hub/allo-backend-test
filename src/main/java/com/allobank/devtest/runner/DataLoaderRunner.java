package com.allobank.devtest.runner;

import com.allobank.devtest.service.StrategyRegistry;
import com.allobank.devtest.store.FinanceDataStore;
import com.allobank.devtest.strategy.IDRDataFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoaderRunner implements ApplicationRunner {

    private final StrategyRegistry registry;
    private final FinanceDataStore store;

    @Override
    public void run(ApplicationArguments args) {

        Map<String, Object> result = new HashMap<>();

        for (IDRDataFetcher strategy : registry.getAll()) {
            Object response = strategy.fetch();

            if (response == null) {
                throw new IllegalStateException("Null response from: " + strategy.getType());
            }

            result.put(strategy.getType(), response);
        }

        store.initialize(result);
    }
}
