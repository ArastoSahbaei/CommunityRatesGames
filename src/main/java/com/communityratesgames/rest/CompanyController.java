package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Company;
import com.communityratesgames.model.CompanyModel;
import lombok.NoArgsConstructor;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

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
            return Response.status(410).build();
        }
        return Response.status(200).build();
    }

    @GET
    @Produces({"application/JSON"})
    public Response showAllCompanies() {
        try {
            List<Company> result = dal.showAllCompanies();
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }
}
