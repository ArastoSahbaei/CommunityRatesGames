package com.communityratesgames.model;

import com.communityratesgames.domain.Company;

import java.io.Serializable;

public class CompanyModel implements Serializable {

    private Long id;
    private String companyName;
    private String country;
    private String city;

    public CompanyModel(){}

    public CompanyModel(Company company){
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.country = company.getCountry();
        this.city = company.getCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
