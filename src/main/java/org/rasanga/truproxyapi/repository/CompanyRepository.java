package org.rasanga.truproxyapi.repository;

import org.rasanga.truproxyapi.entity.Company;
import org.rasanga.truproxyapi.entity.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
//    @Query("select c from Company c left join fetch c.officers o where c.id = ?1 and c.status = ?2 and o.resignedOn is null")
    List<Company> findCompaniesByIdAndStatus(int id, CompanyStatus companyStatus);
//    @Query("select c from Company c join c.officers o where o.resignedOn is null and c.name = ?1 and c.status = ?2")
    List<Company> findCompaniesByNameAndStatus(String name, CompanyStatus companyStatus);

}
