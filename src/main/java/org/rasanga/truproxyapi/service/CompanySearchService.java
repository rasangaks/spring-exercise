package org.rasanga.truproxyapi.service;


import org.rasanga.truproxyapi.dto.Company;

import java.util.List;

public interface CompanySearchService {

    List<Company> getAllCompanies();
    List<Company> searchCompaniesById(String id, boolean allActive);
    List<Company> searchCompaniesByName(String id, boolean allActive);

    void setCompanies(List<Company> companies);
}
