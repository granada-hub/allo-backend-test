package com.allobank.devtest.utils;

import org.springframework.stereotype.Service;

@Service
public class SpreadFactor {
    public double calculateSpreadFactor(String username) {
        int sum = username.toLowerCase().chars().sum();
        return (sum % 1000) / 100000.0;
    }

    public double calculateUsdBuySpread(double rateUsd, double spreadFactor) {
        if (rateUsd == 0) {
            throw new IllegalArgumentException("USD rate cannot be zero");
        }

        return (1 / rateUsd) * (1 + spreadFactor);
    }
}
