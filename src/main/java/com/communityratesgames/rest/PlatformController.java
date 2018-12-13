
package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Platform;
import com.communityratesgames.util.json.*;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/platform")
public class PlatformController {

    @Inject
    private DataAccessLocal dal;

    @GET
    @Produces({"application/JSON"})
    public Response showAllPlatforms() {
        try {
            List<Platform> result = dal.showAllPlatforms();
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response postPlatform(String payload) {
        try {
            JsonObject object = new JsonObject(payload);
            String name = object.getString("name");
            int releaseYear = (int)object.getNumber("releaseYear");
            Long companyId;
            try {
                companyId = (long)object.getNumber("companyId");
            } catch (JsonGetException e) {
                companyId = null;
            }

            Platform platform = dal.createPlatform(name, releaseYear, companyId);
            if (platform == null) {
                return Response.status(Status.NOT_FOUND).entity("{\"error\":1,\"message\":\"company id is missing reference\"}").build();
            }
            String out = new JsonObject()
                .append("name", new JsonString(platform.getName()))
                .append("releaseYear", new JsonNumber(platform.getReleaseYear()))
                .build();
            return Response.ok(out).build();
        } catch (JsonGetException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
        } catch (IOException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (PersistenceException | ParseException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
/*
    private final PlatformService service;

    @Autowired
    public PlatformController(PlatformService service) {
        this.service = service;
    }

    @GetMapping("/platform")
    public ResponseEntity<List<PlatformModel>> getPlatforms() {
        List<PlatformModel> platforms = service.getPlatforms();
        return new ResponseEntity<>(platforms, HttpStatus.OK);
    }

    @PostMapping("/platform")
    public ResponseEntity<List<PlatformModel>> postPlatform(@RequestBody PlatformModel payload) {
        service.insertPlatform(payload);
        return getPlatforms();
    }
*/
}
