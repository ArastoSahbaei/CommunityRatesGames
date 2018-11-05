package com.communityratesgames.Company;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
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

}
