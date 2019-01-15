package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Company;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.util.AuthUtils;
import com.communityratesgames.util.JsonError;

import lombok.NoArgsConstructor;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import static javax.ws.rs.core.Response.Status;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/company")
public class CompanyController {

    @Inject
    private DataAccessLocal dal;

    private CompanyModel companyModel = new CompanyModel();


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
    public Response createCompany(@Context HttpHeaders header, String compis){
        Long token = AuthUtils.getHeaderToken(header);
        if (token == null) {
            return Response.status(Status.UNAUTHORIZED).entity("{\"error\":\"invalid auth token\"}").build();
        }

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

    @GET
    @Path("/byname")
    @Produces("application/json")
    public Response findCompanyByCompanyName(@QueryParam("companyName") String companyName){
        try {
            Company result = dal.findCompanyByCompanyName(companyName);
            if (result == null) {
                return Response.status(Status.NOT_FOUND).entity("null").build();
            }
            return Response.ok(result).build();
        }catch (PersistenceException e){
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
}
