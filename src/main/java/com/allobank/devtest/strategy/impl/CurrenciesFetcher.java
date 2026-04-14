package com.allobank.devtest.strategy.impl;

import com.allobank.devtest.dto.CurrencyDTO;
import com.allobank.devtest.client.FrankfurterClient;
import com.allobank.devtest.strategy.IDRDataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.allobank.devtest.constant.StrategyKeyConst.STRATEGY_AVAILABLE_CURRENCY;

@Component
@RequiredArgsConstructor
public class CurrenciesFetcher implements IDRDataFetcher {

    private final FrankfurterClient client;

    @Override
    public String getType() {
        return STRATEGY_AVAILABLE_CURRENCY;
    }

    @Override
    public Object fetch() {
        List<CurrencyDTO> dtoList = client.getCurrencies();

        if (dtoList == null || dtoList.isEmpty()) {
            throw new IllegalStateException("Currencies data is empty");
        }

        return dtoList;
    }
}
