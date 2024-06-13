package org.rasanga.truproxyapi.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rasanga.truproxyapi.entity.Address;
import org.rasanga.truproxyapi.entity.Company;
import org.rasanga.truproxyapi.entity.CompanyStatus;
import org.rasanga.truproxyapi.entity.Officer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;


    @Test
    public void givenCompanyExists_whenSearchWithCompanayId_thenListOfActiveCompaniesReturned() {
        Address address1 = new Address(1,"p1","adl1", "locality1","USA","12345");
        Address address2 = new Address(2,"p2","adl2", "locality2","USA","12345");
        Officer officer1 = new Officer(1,"John Doe1", "Director",new Date(),null, address1);
        Company company1 = new Company(1, "abc1", "Private", CompanyStatus.ACTIVE, new Date(), address2, List.of(officer1));


        Assertions.assertThat(companyRepository).isNotNull();
        companyRepository.saveAll(List.of(company1));
        List<Company> companies = companyRepository.findCompaniesByIdAndStatus(1, CompanyStatus.ACTIVE);
        Assertions.assertThat(companies.size()).isEqualTo(1);
        Assertions.assertThat(companies.get(0).getId()).isEqualTo(1);
    }

    @Test
    public void givenCompanyExists_whenSearchWithCompanayId_thenIactiveCompaniesNotReturned() {
        Address address1 = new Address(1,"p1","adl1", "locality1","USA","12345");
        Address address2 = new Address(2,"p2","adl2", "locality2","USA","12345");
        Officer officer1 = new Officer(1,"John Doe1", "Director",new Date(),null, address1);
        Company company1 = new Company(1, "abc1", "Private", CompanyStatus.INACTIVE, new Date(), address2, List.of(officer1));


        Assertions.assertThat(companyRepository).isNotNull();
        companyRepository.saveAll(List.of(company1));
        List<Company> companies = companyRepository.findCompaniesByIdAndStatus(1, CompanyStatus.ACTIVE);
        Assertions.assertThat(companies).isEmpty();
    }

}
