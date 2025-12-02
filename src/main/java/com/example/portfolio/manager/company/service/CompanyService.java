package com.example.portfolio.manager.company.service;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.company.model.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {

    Company createCompany(Company company);

    Company findByTicker(String ticker);

    Company findById(Long id);

    Page<Company> findAll(Pageable pageable);

    Page<Company> findBySector(Sector sector, Pageable pageable);

    Page<Company> searchByName(String name, Pageable pageable);

    Company updateCompany(Long id, Company company);

    void deleteCompany(Long id);

    boolean existsByTicker(String ticker);
}