package com.communityratesgames.dao;

import com.communityratesgames.domain.CompanyEntity;
import com.communityratesgames.model.CompanyModel;

import javax.ejb.Local;

@Local
public interface DataAccessLocal {

    public CompanyEntity registerNewCompany(CompanyModel companyModel);
}
