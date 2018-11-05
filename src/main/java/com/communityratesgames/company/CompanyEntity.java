package com.communityratesgames.Company;



import javax.persistence.*;


@Entity
public class CompanyEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "country")
    private String country;

    @Column(name = "location")
    private String location;


    public CompanyEntity(){}

    public CompanyEntity(CompanyModel companyModel){

        this.id = companyModel.getId();
        this.companyName = companyModel.getCompanyName();
        this.country = companyModel.getCountry();
        this.location = companyModel.getLocation();
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
