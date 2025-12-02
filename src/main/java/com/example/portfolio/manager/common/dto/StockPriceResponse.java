package com.example.portfolio.manager.common.dto;

import com.example.portfolio.manager.stock.model.Currency;
import com.example.portfolio.manager.stock.model.StockExchange;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockPriceResponse {

    private Long id;
    private String ticker;
    private String companyName;
    private BigDecimal price;
    private StockExchange exchange;
    private Currency currency;
    private LocalDateTime timestamp;
    private BigDecimal openPrice;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    private BigDecimal closePrice;
    private Long volume;

    public StockPriceResponse(Long id, String ticker, String companyName, BigDecimal price,
                              StockExchange exchange, Currency currency, LocalDateTime timestamp,
                              BigDecimal openPrice, BigDecimal highPrice, BigDecimal lowPrice,
                              BigDecimal closePrice, Long volume) {
        this.id = id;
        this.ticker = ticker;
        this.companyName = companyName;
        this.price = price;
        this.exchange = exchange;
        this.currency = currency;
        this.timestamp = timestamp;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public StockExchange getExchange() { return exchange; }
    public void setExchange(StockExchange exchange) { this.exchange = exchange; }

    public Currency getCurrency() { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public BigDecimal getOpenPrice() { return openPrice; }
    public void setOpenPrice(BigDecimal openPrice) { this.openPrice = openPrice; }

    public BigDecimal getHighPrice() { return highPrice; }
    public void setHighPrice(BigDecimal highPrice) { this.highPrice = highPrice; }

    public BigDecimal getLowPrice() { return lowPrice; }
    public void setLowPrice(BigDecimal lowPrice) { this.lowPrice = lowPrice; }

    public BigDecimal getClosePrice() { return closePrice; }
    public void setClosePrice(BigDecimal closePrice) { this.closePrice = closePrice; }

    public Long getVolume() { return volume; }
    public void setVolume(Long volume) { this.volume = volume; }
}