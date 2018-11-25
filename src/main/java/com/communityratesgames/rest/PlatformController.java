
package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Platform;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
