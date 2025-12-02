package com.example.portfolio.manager.company.repository;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.company.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByTicker(String ticker);

    boolean existsByTicker(String ticker);

    List<Company> findBySector(Sector sector);

    List<Company> findByNameContainingIgnoreCase(String name);
}