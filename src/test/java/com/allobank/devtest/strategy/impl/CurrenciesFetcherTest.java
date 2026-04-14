package com.allobank.devtest.strategy.impl;

import com.allobank.devtest.client.FrankfurterClient;
import com.allobank.devtest.dto.CurrencyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrenciesFetcherTest {

    @Mock
    private FrankfurterClient client;

    @InjectMocks
    private CurrenciesFetcher fetcher;

    @Test
    void shouldConvertMapToCurrencyDTOList() {

        CurrencyDTO dto1 = new CurrencyDTO("USD", "United States Dollar");
        CurrencyDTO dto2 = new CurrencyDTO("IDR", "Indonesian Rupiah");
        List<CurrencyDTO> mockResponse = Arrays.asList(dto1, dto2);

        when(client.getCurrencies()).thenReturn(mockResponse);
        List<CurrencyDTO> result = (List<CurrencyDTO>) fetcher.fetch();
        assertThat(result).hasSize(2);
        assertThat(result)
                .extracting(CurrencyDTO::getCode)
                .containsExactlyInAnyOrder("USD", "IDR");
    }
}
