package com.example.portfolio.manager.company.service;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.company.model.Sector;

import java.util.List;

public interface CompanyService {

    Company createCompany(Company company);

    Company findByTicker(String ticker);

    Company findById(Long id);

    List<Company> findAll();

    List<Company> findBySector(Sector sector);

    List<Company> searchByName(String name);

    Company updateCompany(Long id, Company company);

    void deleteCompany(Long id);

    boolean existsByTicker(String ticker);
}