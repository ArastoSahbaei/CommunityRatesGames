package com.communityratesgames.domain;

import com.communityratesgames.model.CompanyModel;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity
@XmlRootElement
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;


    public Company(){}

    public Company(CompanyModel companyModel){

        this.id = companyModel.getId();
        this.companyName = companyModel.getCompanyName();
        this.country = companyModel.getCountry();
        this.city = companyModel.getCity();
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
