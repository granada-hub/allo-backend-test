package com.allobank.devtest.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Map;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HistoricalRateDTO {
    private double amount;
    private String base;
    private String startDate;
    private String endDate;
    private Map<String, Map<String, Double>> rates;
}
