package com.communityratesgames.model;

import com.communityratesgames.domain.Company;
import lombok.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.Serializable;
import java.io.StringReader;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyModel implements Serializable {

    private Long id;
    private String companyName;
    private String country;
    private String city;



    public JsonObject jsonFromString(String input) {
        JsonReader jsonReader = Json.createReader(new StringReader(input));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object;
    }

    public Company toEntity(String input){
        JsonObject json = jsonFromString(input);
        Company company = new Company();

        companyName = json.getString("companyName");
        country = json.getString("country");
        city = json.getString("city");

        id = company.getId();

        company.setCompanyName(companyName);
        company.setCountry(country);
        company.setCity(city);
        company.setId(id);

        return company;
    }

    public CompanyModel toCompany(Company company){
        CompanyModel cm = new CompanyModel();

        cm.companyName = company.getCompanyName();
        cm.country = company.getCountry();
        cm.city = company.getCity();

        return cm;
    }

    public CompanyModel(Company company){
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.country = company.getCountry();
        this.city = company.getCity();
    }
}
