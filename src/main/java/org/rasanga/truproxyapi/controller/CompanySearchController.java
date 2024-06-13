package org.rasanga.truproxyapi.controller;

import org.rasanga.truproxyapi.dto.Company;
import org.rasanga.truproxyapi.dto.SearchRequest;
import org.rasanga.truproxyapi.dto.SearchResponse;
import org.rasanga.truproxyapi.exception.SearchException;
import org.rasanga.truproxyapi.service.CompanySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@RestController
@RequestMapping("rest/companies/v1")
public class CompanySearchController {

    @Autowired
    private CompanySearchService companySearchService;

    @GetMapping("getAll")
    public List<Company> getAllCompanies() {
        return companySearchService.getAllCompanies();
    }

    @PostMapping("search")
    public SearchResponse search(@RequestBody SearchRequest searchRequest, @RequestParam boolean active) {
        List<Company> companies = null;
        if(StringUtils.isNotEmpty(searchRequest.getCompanyNumber()) && StringUtils.isNumeric(searchRequest.getCompanyNumber())){
            companies = companySearchService.searchCompaniesById(searchRequest.getCompanyNumber(), active);
        }else if(StringUtils.isNotEmpty(searchRequest.getCompanyName())){
            companies = companySearchService.searchCompaniesByName(searchRequest.getCompanyName(), active);
        }else {
            throw new SearchException("Invalid search request");
        }

        return new SearchResponse(companies.size(), companies);
    }


    @PostMapping("add")
    public void addCompanies(@RequestBody List<Company> companies) {
        this.companySearchService.setCompanies(companies);
    }
}
