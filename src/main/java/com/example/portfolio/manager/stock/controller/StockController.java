package com.example.portfolio.manager.stock.controller;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.company.service.CompanyService;
import com.example.portfolio.manager.common.dto.StockPriceRequest;
import com.example.portfolio.manager.common.dto.StockPriceResponse;
import com.example.portfolio.manager.common.util.StockPriceMapper;
import com.example.portfolio.manager.stock.model.StockExchange;
import com.example.portfolio.manager.stock.model.StockPrice;
import com.example.portfolio.manager.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;
    private final CompanyService companyService;

    @Autowired
    public StockController(StockService stockService, CompanyService companyService) {
        this.stockService = stockService;
        this.companyService = companyService;
    }

    @PostMapping("/price")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<StockPriceResponse> addStockPrice(@Valid @RequestBody StockPriceRequest request) {
        Company company = companyService.findByTicker(request.getTicker());
        StockPrice stockPrice = StockPriceMapper.toEntity(request, company);
        StockPrice created = stockService.addStockPrice(stockPrice);
        return ResponseEntity.status(HttpStatus.CREATED).body(StockPriceMapper.toResponse(created));
    }

    @GetMapping("/price/latest/{ticker}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<StockPriceResponse> getLatestPrice(
            @PathVariable String ticker,
            @RequestParam StockExchange exchange) {
        Company company = companyService.findByTicker(ticker);
        StockPrice stockPrice = stockService.getLatestPrice(company, exchange);
        return ResponseEntity.ok(StockPriceMapper.toResponse(stockPrice));
    }

    @GetMapping("/price/history/{ticker}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<StockPriceResponse>> getPriceHistory(
            @PathVariable String ticker,
            @RequestParam StockExchange exchange,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "100") @Max(100) @Min(1) int size) {
        Company company = companyService.findByTicker(ticker);
        Pageable pageable = PageRequest.of(page, size);
        Page<StockPrice> prices = stockService.getPriceHistory(company, exchange, start, end, pageable);
        Page<StockPriceResponse> response = prices.map(StockPriceMapper::toResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/price/all/{ticker}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<StockPriceResponse>> getAllPrices(
            @PathVariable String ticker,
            @RequestParam StockExchange exchange,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "100") @Max(100) @Min(1) int size) {
        Company company = companyService.findByTicker(ticker);
        Pageable pageable = PageRequest.of(page, size);
        Page<StockPrice> prices = stockService.getAllPricesForCompany(company, exchange, pageable);
        Page<StockPriceResponse> response = prices.map(StockPriceMapper::toResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/price/average/{ticker}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BigDecimal> getAveragePrice(
            @PathVariable String ticker,
            @RequestParam StockExchange exchange,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        Company company = companyService.findByTicker(ticker);
        BigDecimal average = stockService.getAveragePrice(company, exchange, start, end);
        return ResponseEntity.ok(average);
    }

    @GetMapping("/volume/total/{ticker}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Long> getTotalVolume(
            @PathVariable String ticker,
            @RequestParam StockExchange exchange,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        Company company = companyService.findByTicker(ticker);
        Long volume = stockService.getTotalVolume(company, exchange, start, end);
        return ResponseEntity.ok(volume);
    }
}