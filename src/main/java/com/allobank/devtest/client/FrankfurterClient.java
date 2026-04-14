package com.allobank.devtest.client;

import com.allobank.devtest.dto.CurrencyDTO;
import com.allobank.devtest.dto.HistoricalRateDTO;
import com.allobank.devtest.dto.LatestRateDTO;
import com.allobank.devtest.constant.ApiEndpoints;
import com.allobank.devtest.utils.SpreadFactor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FrankfurterClient {

    private final WebClient webClient;
    private final SpreadFactor spreadFactor;

    public LatestRateDTO getLatestIDR() {
        LatestRateDTO dto = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiEndpoints.LATEST)
                        .queryParam(ApiEndpoints.PARAM_FROM, "IDR")
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException(
                                        "API Error: " + response.statusCode() + " - " + body
                                ))
                )
                .bodyToMono(LatestRateDTO.class)
                .timeout(Duration.ofSeconds(10))
                .onErrorMap(ex -> new RuntimeException("External API failed", ex))
                .block();

        assert dto != null;
        Map<String, Double> rates = dto.getRates();

        double usdRate = rates.get("USD");
        double sf = spreadFactor.calculateSpreadFactor("granada-hub");
        double buySpread = spreadFactor.calculateUsdBuySpread(usdRate, sf);

        return new LatestRateDTO(
                buySpread,
                dto.getAmount(),
                dto.getBase(),
                dto.getDate(),
                rates
        );
    }

    public HistoricalRateDTO getHistorical(LocalDate from, LocalDate to) {
        String path = ApiEndpoints.HISTORICAL_RANGE
                .replace("{fromDate}", from.toString())
                .replace("{toDate}", to.toString());

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParam(ApiEndpoints.PARAM_FROM, "IDR")
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException(
                                        "API Error: " + response.statusCode() + " - " + body
                                ))
                )
                .bodyToMono(HistoricalRateDTO.class)
                .timeout(Duration.ofSeconds(10))
                .onErrorMap(ex -> new RuntimeException("External API failed", ex))
                .block();
    }

    public List<CurrencyDTO> getCurrencies() {
        try {
            Map<String, String> mapCurrencies = webClient.get()
                    .uri(ApiEndpoints.CURRENCIES)
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException(
                                            "API Error: " + response.statusCode() + " - " + body
                                    ))
                    )
                    .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
                    })
                    .timeout(Duration.ofSeconds(10))
                    .onErrorMap(ex -> new RuntimeException("External API failed", ex))
                    .block();

            if (mapCurrencies == null) {
                throw new IllegalStateException("Currencies response is null");
            }

            return mapCurrencies.entrySet().stream()
                    .map(e -> new CurrencyDTO(e.getKey(), e.getValue()))
                    .toList();

        } catch (Exception e) {
            return List.of();
        }
    }
}