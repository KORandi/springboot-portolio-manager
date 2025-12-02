package com.example.portfolio.manager.company.service.impl;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.company.model.Sector;
import com.example.portfolio.manager.company.repository.CompanyRepository;
import com.example.portfolio.manager.company.service.CompanyService;
import com.example.portfolio.manager.common.exception.DuplicateResourceException;
import com.example.portfolio.manager.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createCompany(Company company) {
        if (existsByTicker(company.getTicker())) {
            throw new DuplicateResourceException("Company with ticker " + company.getTicker() + " already exists");
        }
        return companyRepository.save(company);
    }

    @Override
    @Transactional(readOnly = true)
    public Company findByTicker(String ticker) {
        return companyRepository.findByTicker(ticker)
                .orElseThrow(() -> new ResourceNotFoundException("Company with ticker " + ticker + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Company findById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company with id " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Company> findBySector(Sector sector, Pageable pageable) {
        return companyRepository.findBySector(sector, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Company> searchByName(String name, Pageable pageable) {
        return companyRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Company updateCompany(Long id, Company updatedCompany) {
        Company existingCompany = findById(id);

        if (updatedCompany.getName() != null) {
            existingCompany.setName(updatedCompany.getName());
        }
        if (updatedCompany.getSector() != null) {
            existingCompany.setSector(updatedCompany.getSector());
        }
        if (updatedCompany.getIndustry() != null) {
            existingCompany.setIndustry(updatedCompany.getIndustry());
        }

        return companyRepository.save(existingCompany);
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = findById(id);
        companyRepository.delete(company);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByTicker(String ticker) {
        return companyRepository.existsByTicker(ticker);
    }
}