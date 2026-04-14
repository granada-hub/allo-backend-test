package com.allobank.devtest.service;

import com.allobank.devtest.strategy.IDRDataFetcher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StrategyRegistry {

    private final Map<String, IDRDataFetcher> strategyMap;

    public StrategyRegistry(List<IDRDataFetcher> strategies) {
        this.strategyMap = strategies.stream().collect(Collectors.toMap(IDRDataFetcher::getType, s -> s));
    }

    public IDRDataFetcher get(String type) {
        IDRDataFetcher strategy = strategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Invalid resource type");
        }
        return strategy;
    }

    public Collection<IDRDataFetcher> getAll() {
        return strategyMap.values();
    }

}
