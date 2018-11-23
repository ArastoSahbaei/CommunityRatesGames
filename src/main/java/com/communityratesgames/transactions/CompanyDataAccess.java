package com.communityratesgames.transactions;

import com.communityratesgames.domain.CompanyEntity;
import com.communityratesgames.model.CompanyModel;

import javax.ejb.Local;
import javax.ws.rs.core.Response;

@Local
public interface CompanyDataAccess {

    public abstract CompanyEntity registerNewCompany(CompanyModel companyModel);
}
