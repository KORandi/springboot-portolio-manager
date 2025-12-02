package com.example.portfolio.manager.stock.model;

public enum StockExchange {
    NYSE("New York Stock Exchange"),
    NASDAQ("NASDAQ"),
    LSE("London Stock Exchange"),
    PSE("Prague Stock Exchange"),
    XETRA("Deutsche Börse XETRA");

    private final String displayName;

    StockExchange(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}