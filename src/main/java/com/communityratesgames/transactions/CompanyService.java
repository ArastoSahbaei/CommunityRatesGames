package com.communityratesgames.transactions;

import com.communityratesgames.domain.Company;

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
    public  Company registerNewCompany(Company company) {
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
    public Company findCompanyByCompanyName(String companyName) {
        return em.createQuery("SELECT c FROM Company c WHERE c.company.name = :company", Company.class)
                .setParameter("company", companyName).getSingleResult();
    }


/*
    public CompanyModel findCompanyByCompanyName(String companyName) {
        return new CompanyModel( companyRepository.findCompanyByCompanyName(companyName));
    }

    public List<CompanyModel> findAllCompanies() {
        List<CompanyInterface> listWithAllCompanies = companyRepository.findAll();
        return convertCompanyListToModelList(listWithAllCompanies);
    }

    @Override
    public CompanyModel findCompanyById(Long id) {
        CompanyInterface companyEntity = companyRepository.getOne(id);
        return new CompanyModel(companyEntity);
    }


    private List<CompanyModel> convertCompanyListToModelList(List<CompanyInterface> companyList) {
        List<CompanyModel> companyModelList = new ArrayList<>();
        for (CompanyInterface companyEntity : companyList) {
            companyModelList.add(new CompanyModel(companyEntity));
        }
        return companyModelList;
    }*/
}
