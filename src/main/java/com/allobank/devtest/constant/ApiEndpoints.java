package com.allobank.devtest.constant;

public final class ApiEndpoints {
    private ApiEndpoints() {}
    public static final String V1 = "/v1";
    public static final String LATEST = V1 + "/latest";
    public static final String CURRENCIES = V1 + "/currencies";
    public static final String HISTORICAL_RANGE = V1 + "/{fromDate}..{toDate}";
    public static final String PARAM_FROM = "from";
}
