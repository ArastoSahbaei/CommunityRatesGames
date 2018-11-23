package com.communityratesgames.dao;

import com.communityratesgames.domain.CompanyEntity;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.transactions.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class CRGDataAccess implements DataAccessLocal, DataAccessRemote {

    //Injects to all interfaces for each entity

    @Inject
    private CompanyDataAccess companyDataAccess;
/*
    @Inject
    private GameDataAccess gameDataAccess;

    @Inject
    private PlatformDataAccess platformDataAccess;

    @Inject
    private RatingDataAccess ratingDataAccess;

    @Inject
    private UserDataAccess userDataAccess;
*/
    public CompanyEntity registerNewCompany(CompanyModel companyModel){ return companyDataAccess.registerNewCompany(companyModel); }
}
