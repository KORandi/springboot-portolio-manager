package com.example.portfolio.manager.common.util;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.common.dto.CompanyRequest;
import com.example.portfolio.manager.common.dto.CompanyResponse;

public class CompanyMapper {

    private CompanyMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Company toEntity(CompanyRequest request) {
        Company company = new Company();
        company.setTicker(request.getTicker());
        company.setName(request.getName());
        company.setSector(request.getSector());
        company.setIndustry(request.getIndustry());
        return company;
    }

    public static CompanyResponse toResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getTicker(),
                company.getName(),
                company.getSector(),
                company.getIndustry()
        );
    }
}