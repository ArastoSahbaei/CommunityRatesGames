package com.tobenamed.Company;

import java.io.Serializable;

public class CompanyModel implements Serializable {

    private Long id;
    private String companyName;
    private String country;
    private String location;

    public CompanyModel(){}

    public CompanyModel(CompanyEntity companyEntity){
        this.id = companyEntity.getId();
        this.companyName = companyEntity.getCompanyName();
        this.country = companyEntity.getCountry();
        this.location = companyEntity.getLocation();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
