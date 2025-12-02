package com.example.portfolio.manager.stock.repository;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.stock.model.Currency;
import com.example.portfolio.manager.stock.model.StockExchange;
import com.example.portfolio.manager.stock.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

    Optional<StockPrice> findFirstByCompanyAndExchangeOrderByTimestampDesc(
            Company company, StockExchange exchange);

    List<StockPrice> findByCompanyAndExchangeAndTimestampBetweenOrderByTimestampAsc(
            Company company,
            StockExchange exchange,
            LocalDateTime start,
            LocalDateTime end);

    List<StockPrice> findByCompanyAndExchangeOrderByTimestampDesc(
            Company company, StockExchange exchange);

    List<StockPrice> findByCompanyOrderByTimestampDesc(Company company);

    List<StockPrice> findByCompanyAndCurrency(Company company, Currency currency);

    @Query("SELECT AVG(sp.price) FROM StockPrice sp " +
            "WHERE sp.company = :company AND sp.exchange = :exchange " +
            "AND sp.timestamp BETWEEN :start AND :end")
    BigDecimal findAveragePriceByCompanyExchangeAndPeriod(
            @Param("company") Company company,
            @Param("exchange") StockExchange exchange,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query("SELECT SUM(sp.volume) FROM StockPrice sp " +
            "WHERE sp.company = :company AND sp.exchange = :exchange " +
            "AND sp.timestamp BETWEEN :start AND :end")
    Long findTotalVolumeByCompanyExchangeAndPeriod(
            @Param("company") Company company,
            @Param("exchange") StockExchange exchange,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}