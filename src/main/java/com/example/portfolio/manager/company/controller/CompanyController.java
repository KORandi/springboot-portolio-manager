package com.example.portfolio.manager.company.controller;

import com.example.portfolio.manager.company.model.Company;
import com.example.portfolio.manager.company.model.Sector;
import com.example.portfolio.manager.company.service.CompanyService;
import com.example.portfolio.manager.common.dto.CompanyRequest;
import com.example.portfolio.manager.common.dto.CompanyResponse;
import com.example.portfolio.manager.common.util.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyRequest request) {
        Company company = CompanyMapper.toEntity(request);
        Company created = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(CompanyMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        Company company = companyService.findById(id);
        return ResponseEntity.ok(CompanyMapper.toResponse(company));
    }

    @GetMapping("/ticker/{ticker}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CompanyResponse> getCompanyByTicker(@PathVariable String ticker) {
        Company company = companyService.findByTicker(ticker);
        return ResponseEntity.ok(CompanyMapper.toResponse(company));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        List<Company> companies = companyService.findAll();
        List<CompanyResponse> response = companies.stream()
                .map(CompanyMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sector/{sector}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CompanyResponse>> getCompaniesBySector(@PathVariable Sector sector) {
        List<Company> companies = companyService.findBySector(sector);
        List<CompanyResponse> response = companies.stream()
                .map(CompanyMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CompanyResponse>> searchCompanies(@RequestParam String name) {
        List<Company> companies = companyService.searchByName(name);
        List<CompanyResponse> response = companies.stream()
                .map(CompanyMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request) {
        Company company = CompanyMapper.toEntity(request);
        Company updated = companyService.updateCompany(id, company);
        return ResponseEntity.ok(CompanyMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}