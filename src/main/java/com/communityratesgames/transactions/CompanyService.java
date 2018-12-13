package com.communityratesgames.transactions;

import com.communityratesgames.domain.Company;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.*;
import java.util.List;

@Stateless
@Default
public class CompanyService implements CompanyDataAccess {
    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public Company registerNewCompany(Company company) {
        em.persist(company);
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
        try {
            return em.createQuery("SELECT c FROM Company c WHERE c.company.name = :company", Company.class)
                    .setParameter("company", companyName).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
