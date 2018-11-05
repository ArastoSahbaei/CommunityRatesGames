package com.communityratesgames.Company;

import java.util.List;

public interface CompanyServiceInterface {

    List<CompanyModel> findAllCompanies();
    CompanyModel findCompanyById(Long id);
    CompanyEntity findCompanyByCompanyName(String companyName);
    CompanyModel createNewCompany(CompanyModel companyModel);



}
