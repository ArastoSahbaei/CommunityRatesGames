package com.tobenamed.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    CompanyEntity findCompanyById(Long id);

    CompanyEntity findCompanyByCompanyName(String companyName);

    CompanyEntity findCompanyByCountry(String country);
}
