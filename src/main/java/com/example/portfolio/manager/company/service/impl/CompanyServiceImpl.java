package com.example.portfolio.manager.company.service.impl;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.company.model.Sector;
import com.example.portfolio.manager.company.repository.CompanyRepository;
import com.example.portfolio.manager.company.service.CompanyService;
import com.example.portfolio.manager.common.exception.DuplicateResourceException;
import com.example.portfolio.manager.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> findBySector(Sector sector) {
        return companyRepository.findBySector(sector);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> searchByName(String name) {
        return companyRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Company updateCompany(Long id, Company updatedCompany) {
        Company existingCompany = findById(id);

        // Update fields
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