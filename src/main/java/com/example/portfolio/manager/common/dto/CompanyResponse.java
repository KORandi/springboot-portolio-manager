package com.example.portfolio.manager.common.dto;

import com.example.portfolio.manager.company.model.Industry;
import com.example.portfolio.manager.company.model.Sector;

public class CompanyResponse {

    private Long id;
    private String ticker;
    private String name;
    private Sector sector;
    private Industry industry;

    public CompanyResponse(Long id, String ticker, String name, Sector sector, Industry industry) {
        this.id = id;
        this.ticker = ticker;
        this.name = name;
        this.sector = sector;
        this.industry = industry;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Sector getSector() { return sector; }
    public void setSector(Sector sector) { this.sector = sector; }

    public Industry getIndustry() { return industry; }
    public void setIndustry(Industry industry) { this.industry = industry; }
}