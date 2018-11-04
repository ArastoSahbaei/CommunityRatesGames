package com.tobenamed.Company;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService implements CompanyServiceInterface {

    private final CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public CompanyModel createNewCompany(CompanyModel companyModel) {
        CompanyEntity companyEntity = new CompanyEntity(companyModel);
        return new CompanyModel(companyRepository.save(companyEntity));
    }

    public CompanyEntity findCompanyByCompanyName(String companyName){
        return companyRepository.findCompanyByCompanyName(companyName);
    }

    public List<CompanyModel> findAllCompanies(){
        List<CompanyEntity> listWithAllCompanies = companyRepository.findAll();
        return convertCompanyListToModelList(listWithAllCompanies);
    }

    @Override
    public CompanyModel findCompanyById(Long id) {
        CompanyEntity companyEntity = companyRepository.getOne(id);
        return new CompanyModel(companyEntity);
    }


    private List<CompanyModel> convertCompanyListToModelList (List<CompanyEntity> companyList){
        List<CompanyModel> companyModelList = new ArrayList<>();
        for (CompanyEntity companyEntity : companyList) {
            companyModelList.add(new CompanyModel(companyEntity));
        }
        return companyModelList;
    }
}
