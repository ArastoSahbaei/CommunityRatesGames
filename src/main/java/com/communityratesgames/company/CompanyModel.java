package com.communityratesgames.company;

import java.io.Serializable;

public class CompanyModel implements Serializable {

    private Long id;
    private String companyName;
    private String country;
    private String city;

    public CompanyModel(){}

    public CompanyModel(CompanyEntity companyEntity){
        this.id = companyEntity.getId();
        this.companyName = companyEntity.getCompanyName();
        this.country = companyEntity.getCountry();
        this.city = companyEntity.getCity();
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
}
