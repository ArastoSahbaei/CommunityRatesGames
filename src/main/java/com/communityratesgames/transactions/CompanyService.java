package com.communityratesgames.transactions;

import com.communityratesgames.domain.CompanyEntity;
import com.communityratesgames.model.CompanyModel;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

@Stateless
@Default
public class CompanyService implements CompanyDataAccess {


    @PersistenceContext()
    private EntityManager em;

    @Override
    public CompanyEntity registerNewCompany(CompanyModel companyModel) {
        CompanyEntity companyEntity = new CompanyEntity(companyModel);
        em.persist(companyEntity);
        em.flush();

        return companyEntity;
    }


/*
    public CompanyModel findCompanyByCompanyName(String companyName) {
        return new CompanyModel( companyRepository.findCompanyByCompanyName(companyName));
    }

    public List<CompanyModel> findAllCompanies() {
        List<CompanyEntity> listWithAllCompanies = companyRepository.findAll();
        return convertCompanyListToModelList(listWithAllCompanies);
    }

    @Override
    public CompanyModel findCompanyById(Long id) {
        CompanyEntity companyEntity = companyRepository.getOne(id);
        return new CompanyModel(companyEntity);
    }


    private List<CompanyModel> convertCompanyListToModelList(List<CompanyEntity> companyList) {
        List<CompanyModel> companyModelList = new ArrayList<>();
        for (CompanyEntity companyEntity : companyList) {
            companyModelList.add(new CompanyModel(companyEntity));
        }
        return companyModelList;
    }*/
}
