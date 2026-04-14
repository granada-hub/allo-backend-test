package com.allobank.devtest.store;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class FinanceDataStore {

    private final Map<String, Object> store = new ConcurrentHashMap<>();
    @Getter
    private volatile boolean initialized = false;

    public synchronized void initialize(Map<String, Object> data) {
        if (initialized) return;

        store.putAll(Collections.unmodifiableMap(data));
        initialized = true;
    }

    public Object get(String key) {
        return store.get(key);
    }
}