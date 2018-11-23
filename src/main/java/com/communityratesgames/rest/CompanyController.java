package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.transactions.CompanyService;
import lombok.NoArgsConstructor;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@NoArgsConstructor
@Stateless
@Path("/company")
public class CompanyController {

    @Inject
    private DataAccessLocal dal;

    @POST
    @Produces({"application/JSON"})
    @Consumes({"application/JSON"})
    public Response registerNewCompany(CompanyModel companyModel) {
        try {
            dal.registerNewCompany(companyModel);
        } catch (ServiceUnavailableException e ) {
            e.printStackTrace();
        }
        return Response.status(200).build();
    }
/*
    public ResponseEntity<List<CompanyModel>> getAllCompanies() {
        List<CompanyModel> resultList = companyService.findAllCompanies();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    public ResponseEntity<CompanyModel> getCompanyById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(companyService.findCompanyById(id), HttpStatus.OK);
    }

    public ResponseEntity<CompanyModel> getCompanyByCompanyName(@RequestParam("name") String companyName) {
        return new ResponseEntity<>(companyService.findCompanyByCompanyName(companyName), HttpStatus.OK);
    }
*/
}
