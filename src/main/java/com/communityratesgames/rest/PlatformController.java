
package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Platform;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
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
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(payload);
            JsonNode name = node.findValue("name");
            if (name == null) {
                return Response.status(400).entity("{\"error\":\"Name not specified.\"}").build();
            }

            JsonNode releaseYear = node.findValue("releaseYear");
            if (name == null) {
                return Response.status(400).entity("{\"error\":\"Release year not specified.\"}").build();
            }

            JsonNode companyId = node.findValue("companyId");
            Platform platform = dal.createPlatform(name.asText(), releaseYear.asInt(), (companyId == null) ? null : companyId.asLong());
            return Response.ok(platform).build();
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
