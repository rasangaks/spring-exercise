package org.rasanga.truproxyapi.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.rasanga.truproxyapi.dto.Address;
import org.rasanga.truproxyapi.dto.Company;
import org.rasanga.truproxyapi.dto.CompanyStatus;
import org.rasanga.truproxyapi.dto.Officer;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "company.id")
    @Mapping(target = "name", source = "company.name")
    @Mapping(target = "type", source = "company.type")
    @Mapping(target = "status", source = "company.status")
    @Mapping(target = "createdOn", source = "company.createdOn")
    @Mapping(target = "address", source = "company.address")
    @Mapping(target = "officers", source = "company.officers")
    Company mapEntityCompany(org.rasanga.truproxyapi.entity.Company company);
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "company.id")
    @Mapping(target = "name", source = "company.name")
    @Mapping(target = "type", source = "company.type")
    @Mapping(target = "status", source = "company.status")
    @Mapping(target = "createdOn", source = "company.createdOn")
    @Mapping(target = "address", source = "company.address")
    @Mapping(target = "officers", source = "company.officers")
    org.rasanga.truproxyapi.entity.Company mapDtoCompany(Company company);
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "officer.name")
    @Mapping(target = "role", source = "officer.role")
    @Mapping(target = "appointedOn", source = "officer.appointedOn")
    @Mapping(target = "address", source = "officer.address")
    Officer mapEntityOfficer(org.rasanga.truproxyapi.entity.Officer officer);
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "officer.name")
    @Mapping(target = "role", source = "officer.role")
    @Mapping(target = "appointedOn", source = "officer.appointedOn")
    @Mapping(target = "address", source = "officer.address")
    org.rasanga.truproxyapi.entity.Officer mapDtoOfficer(Officer officer);
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "premises", source = "address.premises")
    @Mapping(target = "addressLine1", source = "address.addressLine1")
    @Mapping(target = "locality", source = "address.locality")
    @Mapping(target = "country", source = "address.country")
    @Mapping(target = "postalCode", source = "address.postalCode")
    Address mapEntityAddress(org.rasanga.truproxyapi.entity.Address address);
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "premises", source = "address.premises")
    @Mapping(target = "addressLine1", source = "address.addressLine1")
    @Mapping(target = "locality", source = "address.locality")
    @Mapping(target = "country", source = "address.country")
    @Mapping(target = "postalCode", source = "address.postalCode")
    org.rasanga.truproxyapi.entity.Address mapDtoAddress(Address address);
    CompanyStatus mapEntityCompanyStatus(org.rasanga.truproxyapi.entity.CompanyStatus companyStatus);
    org.rasanga.truproxyapi.entity.CompanyStatus mapDtoCompanyStatus(CompanyStatus companyStatus);
}
