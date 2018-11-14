package com.communityratesgames.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/company")
    public ResponseEntity<CompanyModel> RegisterNewCompany(@RequestBody CompanyModel companyModel) {
        CompanyModel newCompanyModel = companyService.createNewCompany(companyModel);
        return new ResponseEntity<>(newCompanyModel, HttpStatus.OK);
    }

    @GetMapping("/company")
    public ResponseEntity<List<CompanyModel>> getAllCompanies() {
        List<CompanyModel> resultList = companyService.findAllCompanies();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyModel> getCompanyById(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.findCompanyById(id), HttpStatus.OK);
    }

    @GetMapping("/company/{companyName}")
    public ResponseEntity<CompanyModel> getCompanyByCompanyName(@PathVariable String companyName) {
        return new ResponseEntity<>(companyService.findCompanyByCompanyName(companyName), HttpStatus.OK);
    }

}
