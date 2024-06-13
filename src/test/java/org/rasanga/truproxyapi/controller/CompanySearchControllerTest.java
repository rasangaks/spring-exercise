package org.rasanga.truproxyapi.controller;

import jakarta.servlet.ServletException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rasanga.truproxyapi.SpringExerciseApplication;
import org.rasanga.truproxyapi.dto.Address;
import org.rasanga.truproxyapi.dto.Company;
import org.rasanga.truproxyapi.dto.CompanyStatus;
import org.rasanga.truproxyapi.dto.Officer;
import org.rasanga.truproxyapi.service.CompanySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringExerciseApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class CompanySearchControllerTest {

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final String AUTH_TOKEN = "PwewCEztSW7XlaAKqkg4IaOsPelGynw6SN9WsbNf";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanySearchService companySearchService;

    @Before
    public void setUp() {
        Address address1 = new Address("p1","adl1", "locality1","USA","12345");
        Address address2 = new Address("p2","adl2", "locality2","USA","12345");
        Officer officer1 = new Officer("John Doe", "Director", new Date(), address1);
        Officer officer2 = new Officer("John Doe2", "Director", new Date(), address2);
        Company company1 = new Company(1, "abc", "Private", CompanyStatus.ACTIVE, new Date(), address2, List.of(officer1));
        Company company2 = new Company(2, "abcd", "Private", CompanyStatus.ACTIVE, new Date(), address2, List.of(officer2));
        Mockito.when(companySearchService.searchCompaniesById(Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(List.of(company1));
        Mockito.when(companySearchService.searchCompaniesByName(Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(List.of(company2));
    }

    @Test
    public void GivenValidApiKeyAndRequest_WhenSearchCalled_ThenRequestAccepted() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/rest/companies/v1/search?active=true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN)
                        .content("{\n" +
                                "    \"companyName\" : \"abc\",\n" +
                                "    \"companyNumber\" : \"1\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total_results", Matchers.is(1)));
    }

    @Test(expected = ServletException.class)
    public void GivenInvalidApiKeyAndRequest_WhenSearchCalled_ThenRequestAccepted() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/rest/companies/v1/search?active=true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_TOKEN_HEADER_NAME, "invalid")
                        .content("{\n" +
                                "    \"companyName\" : \"abc\",\n" +
                                "    \"companyNumber\" : \"1\"\n" +
                                "}"))
                .andExpect(__ -> MatcherAssert.assertThat(__.getResolvedException(),
                Matchers.instanceOf(Exception.class)));
    }

    @Test
    public void GivenValidApiKeyAndRequestEmpty_WhenSearchCalled_ThenRequestFails() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/rest/companies/v1/search?active=true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN)
                        .content("{\n" +
                                "    \"companyName\" : \"\",\n" +
                                "    \"companyNumber\" : \"\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void GivenValidApiKeyAndRequest_WhenSearchCalledWithIdAndName_ThenIdIsUsedToSearch() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/rest/companies/v1/search?active=true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN)
                        .content("{\n" +
                                "    \"companyName\" : \"abc\",\n" +
                                "    \"companyNumber\" : \"1\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total_results", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].title", Matchers.is("abc")));
    }

    @Test
    public void GivenValidApiKeyAndRequest_WhenSearchCalledWithIdEmptyAndName_ThenNameIsUsedToSearch() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/rest/companies/v1/search?active=true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN)
                        .content("{\n" +
                                "    \"companyName\" : \"abc\",\n" +
                                "    \"companyNumber\" : \"\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total_results", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].title", Matchers.is("abcd")));
    }

}
