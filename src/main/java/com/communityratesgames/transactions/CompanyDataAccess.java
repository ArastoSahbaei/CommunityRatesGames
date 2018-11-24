package com.communityratesgames.transactions;

import com.communityratesgames.domain.CompanyEntity;
import com.communityratesgames.model.CompanyModel;

import javax.ejb.Local;
import javax.ws.rs.core.Response;
import java.util.List;

@Local
public interface CompanyDataAccess {

    public abstract CompanyEntity registerNewCompany(CompanyModel companyModel);
    public abstract List<CompanyEntity> showAllCompanies();
}
