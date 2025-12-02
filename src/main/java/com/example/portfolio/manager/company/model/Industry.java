package com.example.portfolio.manager.company.model;

public enum Industry {
    // Technology
    SOFTWARE("Software"),
    SEMICONDUCTORS("Semiconductors"),
    CONSUMER_ELECTRONICS("Consumer Electronics"),
    IT_SERVICES("IT Services"),

    // Financials
    BANKS("Banks"),
    INSURANCE("Insurance"),
    INVESTMENT_BANKING("Investment Banking"),

    // Healthcare
    PHARMACEUTICALS("Pharmaceuticals"),
    BIOTECHNOLOGY("Biotechnology"),
    MEDICAL_DEVICES("Medical Devices"),

    // Consumer
    AUTOMOTIVE("Automotive"),
    RETAIL("Retail"),
    FOOD_BEVERAGE("Food & Beverage"),

    // Energy
    OIL_GAS("Oil & Gas"),
    RENEWABLE_ENERGY("Renewable Energy"),

    // Other
    AEROSPACE_DEFENSE("Aerospace & Defense"),
    TELECOMMUNICATIONS("Telecommunications"),
    REAL_ESTATE("Real Estate");

    private final String displayName;

    Industry(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}