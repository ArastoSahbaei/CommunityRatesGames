package com.communityratesgames.model;

import com.communityratesgames.domain.Company;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.Serializable;
import java.io.StringReader;

public class CompanyModel implements Serializable {

    private Long id;
    private String companyName;
    private String country;
    private String city;

    public CompanyModel() {}

    private JsonObject jsonObjectFromString(String input) {
        JsonReader jr = Json.createReader(new StringReader(input));
        JsonObject jsonObject = jr.readObject();

        jr.close();
        return jsonObject;
    }

    public Company jsonPtoEntity(String companyEntity){
        JsonObject jsonP = jsonObjectFromString(companyEntity);
        Company company = new Company();

        companyName = jsonP.getString("companyName");
        country = jsonP.getString("country");
        city = jsonP.getString("city");
        id = company.getId();

        company.setCompanyName(companyName);
        company.setCountry(country);
        company.setCity(city);
        company.setId(id);

        return company;
    }

    public CompanyModel toCompany(Company company){
        CompanyModel companyModel = new CompanyModel();

        companyModel.companyName = company.getCompanyName();
        companyModel.country = company.getCountry();
        companyModel.city = company.getCity();
        return companyModel;
    }

    public CompanyModel(Company company){
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.country = company.getCountry();
        this.city = company.getCity();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
