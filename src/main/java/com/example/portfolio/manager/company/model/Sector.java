package com.example.portfolio.manager.company.model;

public enum Sector {
    TECHNOLOGY("Technology"),
    FINANCIALS("Financials"),
    HEALTHCARE("Healthcare"),
    CONSUMER_DISCRETIONARY("Consumer Discretionary"),
    CONSUMER_STAPLES("Consumer Staples"),
    ENERGY("Energy"),
    INDUSTRIALS("Industrials"),
    MATERIALS("Materials"),
    REAL_ESTATE("Real Estate"),
    UTILITIES("Utilities"),
    TELECOMMUNICATIONS("Telecommunications");

    private final String displayName;

    Sector(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}