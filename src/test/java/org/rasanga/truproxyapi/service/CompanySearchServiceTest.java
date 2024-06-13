package org.rasanga.truproxyapi.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;
import org.rasanga.truproxyapi.entity.Address;
import org.rasanga.truproxyapi.entity.Company;
import org.rasanga.truproxyapi.entity.CompanyStatus;
import org.rasanga.truproxyapi.entity.Officer;
import org.rasanga.truproxyapi.repository.CompanyRepository;
import org.rasanga.truproxyapi.utils.CompanyMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CompanySearchServiceImpl.class, CompanyMapperImpl.class})
public class CompanySearchServiceTest {

    @Autowired
    private CompanySearchServiceImpl companySearchService;

    @MockBean
    private CompanyRepository companyRepository;




    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Address address1 = new Address(1,"p1","adl1", "locality1","USA","12345");
        Address address2 = new Address(2,"p2","adl2", "locality2","USA","12345");

        Officer officer1 = new Officer(1,"John Doe1", "Director",new Date(),null, address1);
        Officer officer2 = new Officer(2,"John Doe2", "Director",new Date(),new Date(), address1);

        List<Officer> officers = new ArrayList<>();
        officers.add(officer1);
        officers.add(officer2);

        Company company1 = new Company(1, "abc1", "Private", CompanyStatus.ACTIVE, new Date(), address2, officers);

        Mockito.when(companyRepository.findCompaniesByIdAndStatus(Mockito.anyInt(), Mockito.any(CompanyStatus.class)))
                .thenReturn(Arrays.asList(company1));

    }

    @Test
    public void GivenCompaniesExist_WhenSearchWithId_thenCompaniesReturnedWithoutResignedOfficers() {
        List<org.rasanga.truproxyapi.dto.Company> companies = companySearchService.searchCompaniesById("1", true);
        Assertions.assertThat(companies).isNotNull();
        Assertions.assertThat(companies).size().isEqualTo(1);
        Assertions.assertThat(companies.get(0).getOfficers()).size().isEqualTo(1);
        Assertions.assertThat(companies.get(0).getOfficers().get(0).getName()).isEqualTo("John Doe1");
    }

}
