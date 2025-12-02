package com.example.portfolio.manager.common.dto;

import com.example.portfolio.manager.company.model.Industry;
import com.example.portfolio.manager.company.model.Sector;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyRequest {

    @NotBlank(message = "Ticker is required")
    @Size(min = 1, max = 10, message = "Ticker must be between 1 and 10 characters")
    private String ticker;

    @NotBlank(message = "Company name is required")
    private String name;

    private Sector sector;

    private Industry industry;

    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Sector getSector() { return sector; }
    public void setSector(Sector sector) { this.sector = sector; }

    public Industry getIndustry() { return industry; }
    public void setIndustry(Industry industry) { this.industry = industry; }
}