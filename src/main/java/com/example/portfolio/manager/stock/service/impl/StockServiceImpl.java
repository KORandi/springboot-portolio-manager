package com.example.portfolio.manager.stock.service.impl;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.common.exception.ResourceNotFoundException;
import com.example.portfolio.manager.stock.model.Currency;
import com.example.portfolio.manager.stock.model.StockExchange;
import com.example.portfolio.manager.stock.model.StockPrice;
import com.example.portfolio.manager.stock.repository.StockPriceRepository;
import com.example.portfolio.manager.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    private final StockPriceRepository stockPriceRepository;

    @Autowired
    public StockServiceImpl(StockPriceRepository stockPriceRepository) {
        this.stockPriceRepository = stockPriceRepository;
    }

    @Override
    public StockPrice addStockPrice(StockPrice stockPrice) {
        if (stockPrice.getTimestamp() == null) {
            stockPrice.setTimestamp(LocalDateTime.now());
        }
        return stockPriceRepository.save(stockPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public StockPrice getLatestPrice(Company company, StockExchange exchange) {
        return stockPriceRepository.findFirstByCompanyAndExchangeOrderByTimestampDesc(company, exchange)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No price found for company " + company.getTicker() + " on " + exchange));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StockPrice> getPriceHistory(Company company, StockExchange exchange,
                                            LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return stockPriceRepository.findByCompanyAndExchangeAndTimestampBetweenOrderByTimestampAsc(
                company, exchange, start, end, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StockPrice> getAllPricesForCompany(Company company, StockExchange exchange, Pageable pageable) {
        return stockPriceRepository.findByCompanyAndExchangeOrderByTimestampDesc(company, exchange, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getAveragePrice(Company company, StockExchange exchange,
                                      LocalDateTime start, LocalDateTime end) {
        BigDecimal average = stockPriceRepository.findAveragePriceByCompanyExchangeAndPeriod(
                company, exchange, start, end);

        if (average == null) {
            throw new ResourceNotFoundException(
                    "No price data found for company " + company.getTicker() +
                            " on " + exchange + " in specified period");
        }

        return average;
    }

    @Override
    @Transactional(readOnly = true)
    public Long getTotalVolume(Company company, StockExchange exchange,
                               LocalDateTime start, LocalDateTime end) {
        Long volume = stockPriceRepository.findTotalVolumeByCompanyExchangeAndPeriod(
                company, exchange, start, end);

        return volume != null ? volume : 0L;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockPrice> getPricesByCurrency(Company company, Currency currency) {
        return stockPriceRepository.findByCompanyAndCurrency(company, currency);
    }
}