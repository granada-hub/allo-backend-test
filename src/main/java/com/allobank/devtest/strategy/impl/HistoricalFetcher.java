package com.allobank.devtest.strategy.impl;

import com.allobank.devtest.dto.HistoricalRateDTO;
import com.allobank.devtest.client.FrankfurterClient;
import com.allobank.devtest.strategy.IDRDataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.allobank.devtest.constant.StrategyKeyConst.STRATEGY_HISTORICAL;

@Component
@RequiredArgsConstructor
public class HistoricalFetcher implements IDRDataFetcher {

    private final FrankfurterClient client;

    @Override
    public String getType() {
        return STRATEGY_HISTORICAL;
    }

    @Override
    public Object fetch() {
        HistoricalRateDTO dto = client.getHistorical(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10));

        if (dto == null) {
            throw new IllegalStateException("Currencies data is empty");
        }

        return dto;
    }
}
