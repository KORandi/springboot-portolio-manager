package com.example.portfolio.manager.stock.service;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.stock.model.Currency;
import com.example.portfolio.manager.stock.model.StockExchange;
import com.example.portfolio.manager.stock.model.StockPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface StockService {

    StockPrice addStockPrice(StockPrice stockPrice);

    StockPrice getLatestPrice(Company company, StockExchange exchange);

    List<StockPrice> getPriceHistory(Company company, StockExchange exchange,
                                     LocalDateTime start, LocalDateTime end);

    List<StockPrice> getAllPricesForCompany(Company company, StockExchange exchange);

    BigDecimal getAveragePrice(Company company, StockExchange exchange,
                               LocalDateTime start, LocalDateTime end);

    Long getTotalVolume(Company company, StockExchange exchange,
                        LocalDateTime start, LocalDateTime end);

    List<StockPrice> getPricesByCurrency(Company company, Currency currency);
}