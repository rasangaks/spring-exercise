package org.rasanga.truproxyapi.service;

import org.rasanga.truproxyapi.dto.Company;
import org.rasanga.truproxyapi.entity.CompanyStatus;
import org.rasanga.truproxyapi.repository.CompanyRepository;
import org.rasanga.truproxyapi.utils.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CompanySearchServiceImpl implements CompanySearchService{

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public List<Company> searchCompaniesById(String id, boolean active) {
        List<org.rasanga.truproxyapi.entity.Company> companies = companyRepository.findCompaniesByIdAndStatus(Integer.parseInt(id), active ? CompanyStatus.ACTIVE : CompanyStatus.INACTIVE);
        for (org.rasanga.truproxyapi.entity.Company company : companies) {
            company.getOfficers().removeIf(officer -> officer.getResignedOn() != null);
        }
       return companies.stream().map(company -> companyMapper.mapEntityCompany(company)).toList(   );
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll().stream().map(company -> companyMapper.mapEntityCompany(company)).toList();
    }

    @Override
    public List<org.rasanga.truproxyapi.dto.Company> searchCompaniesByName(String name, boolean active) {
        List<org.rasanga.truproxyapi.entity.Company> companies = companyRepository.findCompaniesByNameAndStatus(name, active ? CompanyStatus.ACTIVE : CompanyStatus.INACTIVE);
        for (org.rasanga.truproxyapi.entity.Company company : companies) {
            company.getOfficers().removeIf(officer -> officer.getResignedOn() != null);
        }
        return companies.stream().map(company -> companyMapper.mapEntityCompany(company)).toList(   );
    }

    @Override
    public void setCompanies(List<org.rasanga.truproxyapi.dto.Company> companies) {
        this.companyRepository.saveAll(companies.stream().map(company -> companyMapper.mapDtoCompany(company)).toList());
    }
}
