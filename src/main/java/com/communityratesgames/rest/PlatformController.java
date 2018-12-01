
package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Platform;
import com.communityratesgames.util.json.*;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
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
        } catch ( Exception e ) {
            return Response.status(404).build();
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
            String out = new JsonObject()
                .append("name", new JsonString(platform.getName()))
                .append("releaseYear", new JsonNumber(platform.getReleaseYear()))
                .build();
            return Response.ok(out).build();
        } catch (JsonGetException e) {
            return Response.status(400).entity(e.getMessage()).build();
        } catch ( Exception e ) {
            return Response.status(413).entity(e.getMessage()).build();
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
