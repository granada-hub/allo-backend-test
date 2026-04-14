package com.allobank.devtest.strategy.impl;

import com.allobank.devtest.client.FrankfurterClient;
import com.allobank.devtest.utils.SpreadFactor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LatestRatesFetcherTest {

    @Mock
    private FrankfurterClient client;

    @InjectMocks
    private LatestRatesFetcher fetcher;

    @Mock
    private SpreadFactor spreadFactor;

    @Test
    void shouldCalculateUsdBuySpreadCorrectly() {
        double usdRate = 0.000058;
        double sf = 1.2;
        when(spreadFactor.calculateUsdBuySpread(anyDouble(), anyDouble())).thenCallRealMethod();
        double result = spreadFactor.calculateUsdBuySpread(usdRate, sf);
        assertThat(result).isGreaterThan(0);
    }
}
