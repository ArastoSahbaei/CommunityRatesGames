package com.communityratesgames.transactions;

import com.communityratesgames.domain.Company;
import com.communityratesgames.model.CompanyModel;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Default
public class CompanyService implements CompanyDataAccess {


    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public Company registerNewCompany(CompanyModel companyModel) {
        Company company = new Company(companyModel);
        em.persist(company);
        em.flush();

        return company;
    }

    @Override
    public List<Company> showAllCompanies() {
        Query q = em.createNativeQuery("SELECT * FROM company_entity", Company.class);
        List<Company> companies = q.getResultList();
        return companies;
    }

    @Override
    public Company getCompanyFromName(String name) {
        return em.createQuery("SELECT c FROM Company c WHERE c.companyName = :companyName", Company.class)
                .setParameter("companyName", name)
                .getSingleResult();
    }


/*
    public CompanyModel findCompanyByCompanyName(String companyName) {
        return new CompanyModel( companyRepository.findCompanyByCompanyName(companyName));
    }

    public List<CompanyModel> findAllCompanies() {
        List<Company> listWithAllCompanies = companyRepository.findAll();
        return convertCompanyListToModelList(listWithAllCompanies);
    }

    @Override
    public CompanyModel findCompanyById(Long id) {
        Company companyEntity = companyRepository.getOne(id);
        return new CompanyModel(companyEntity);
    }


    private List<CompanyModel> convertCompanyListToModelList(List<Company> companyList) {
        List<CompanyModel> companyModelList = new ArrayList<>();
        for (Company companyEntity : companyList) {
            companyModelList.add(new CompanyModel(companyEntity));
        }
        return companyModelList;
    }*/
}
