package com.communityratesgames.transactions;

import com.communityratesgames.domain.Company;
import com.communityratesgames.model.CompanyModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CompanyDataAccess {

    public abstract Company registerNewCompany(Company company);
    public abstract List<Company> showAllCompanies();
    public abstract Company findCompanyByCompanyName(String companyName);
}
