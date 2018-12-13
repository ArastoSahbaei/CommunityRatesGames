package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Company;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.util.JsonError;

import lombok.NoArgsConstructor;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/company")
public class CompanyController {

    @Inject
    private DataAccessLocal dal;

    private CompanyModel companyModel = new CompanyModel();

 //   @POST
 //   @Produces({"application/JSON"})
 //   @Consumes({"application/JSON"})
 //   public Response registerNewCompany(CompanyModel companyModel) {
 //       try {
 //           dal.registerNewCompany(companyModel);
 //       } catch (ServiceUnavailableException e ) {
 //           return Response.status(410).build();
 //       }
 //       return Response.status(200).build();
 //   }

    @GET
    @Produces({"application/JSON"})
    public Response showAllCompanies() {
        try {
            List<Company> result = dal.showAllCompanies();
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createCompany(String compis){
        try {
            Company toEntity = companyModel.jsonPtoEntity(compis);
            Company company2 = dal.registerNewCompany(toEntity);
            CompanyModel toCompany = companyModel.toCompany(company2);
            return Response.ok(toCompany).build();
        } catch (JsonError e) {
            return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
