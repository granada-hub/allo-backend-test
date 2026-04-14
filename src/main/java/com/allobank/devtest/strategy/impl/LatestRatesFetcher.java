package com.allobank.devtest.strategy.impl;

import com.allobank.devtest.dto.LatestRateDTO;
import com.allobank.devtest.client.FrankfurterClient;
import com.allobank.devtest.strategy.IDRDataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.allobank.devtest.constant.StrategyKeyConst.STRATEGY_LATEST_RATES;

@Component
@RequiredArgsConstructor
public class LatestRatesFetcher implements IDRDataFetcher {

    private final FrankfurterClient client;

    @Override
    public String getType() {
        return STRATEGY_LATEST_RATES;
    }

    @Override
    public Object fetch() {
        LatestRateDTO dto = client.getLatestIDR();

        if (dto == null) {
            throw new IllegalStateException("Currencies data is empty");
        }

        return dto;
    }
}
