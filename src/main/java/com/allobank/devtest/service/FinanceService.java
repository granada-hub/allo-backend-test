package com.allobank.devtest.service;

import com.allobank.devtest.store.FinanceDataStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private final FinanceDataStore store;

    public Object getData(String resourceType) {
        Object data = store.get(resourceType);

        // refer to StrategyKeyConst
        if (data == null) {
            throw new RuntimeException("unknown resource type");
        }

        return data;
    }
}