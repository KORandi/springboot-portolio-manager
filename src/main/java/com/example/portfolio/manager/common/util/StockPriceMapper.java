package com.example.portfolio.manager.common.util;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.common.dto.StockPriceRequest;
import com.example.portfolio.manager.common.dto.StockPriceResponse;
import com.example.portfolio.manager.stock.model.StockPrice;

import java.time.LocalDateTime;

public class StockPriceMapper {

    private StockPriceMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static StockPrice toEntity(StockPriceRequest request, Company company) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.setCompany(company);
        stockPrice.setPrice(request.getPrice());
        stockPrice.setExchange(request.getExchange());
        stockPrice.setCurrency(request.getCurrency());
        stockPrice.setOpenPrice(request.getOpenPrice());
        stockPrice.setHighPrice(request.getHighPrice());
        stockPrice.setLowPrice(request.getLowPrice());
        stockPrice.setClosePrice(request.getClosePrice());
        stockPrice.setVolume(request.getVolume());
        stockPrice.setTimestamp(LocalDateTime.now());
        return stockPrice;
    }

    public static StockPriceResponse toResponse(StockPrice stockPrice) {
        return new StockPriceResponse(
                stockPrice.getId(),
                stockPrice.getCompany().getTicker(),
                stockPrice.getCompany().getName(),
                stockPrice.getPrice(),
                stockPrice.getExchange(),
                stockPrice.getCurrency(),
                stockPrice.getTimestamp(),
                stockPrice.getOpenPrice(),
                stockPrice.getHighPrice(),
                stockPrice.getLowPrice(),
                stockPrice.getClosePrice(),
                stockPrice.getVolume()
        );
    }
}