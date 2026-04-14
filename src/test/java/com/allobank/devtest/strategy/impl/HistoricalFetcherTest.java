package com.allobank.devtest.strategy.impl;

import com.allobank.devtest.client.FrankfurterClient;
import com.allobank.devtest.dto.HistoricalRateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoricalFetcherTest {

    @Mock
    private FrankfurterClient client;

    @InjectMocks
    private HistoricalFetcher fetcher;

    @Test
    void shouldMapHistoricalRatesCorrectly() {
        HistoricalRateDTO mockResponse = new HistoricalRateDTO();
        mockResponse.setBase("IDR");
        Map<String, Map<String, Double>> rates = Map.of("2024-01-01", Map.of("USD", 0.00006));
        mockResponse.setRates(rates);

        when(client.getHistorical(any(), any())).thenReturn(mockResponse);
        HistoricalRateDTO result = (HistoricalRateDTO) fetcher.fetch();
        assertThat(result.getRates()).isNotNull();
        assertThat(result.getRates()).containsKey("2024-01-01");
        assertThat(result.getRates().get("2024-01-01")).containsEntry("USD", 0.00006);
    }
}