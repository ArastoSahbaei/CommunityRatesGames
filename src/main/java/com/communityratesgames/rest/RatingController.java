package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Rating;
import com.communityratesgames.model.RatingModel;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/rating")
public class RatingController {
    private final static Logger logger = Logger.getLogger(com.communityratesgames.rest.RatingController.class);

    @Inject
    private DataAccessLocal dal;

    @GET
    @Produces({"application/JSON"})
    public Response showAllRatings() {
        try {
            List<Rating> result = dal.showAllRatings();
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }
    @GET
    @Path("/bygame")
    @Produces({"application/JSON"})
    public Response findRatingsByGameId(@QueryParam("title") String title) {
        try {
            List<Rating> result = dal.findRatingsByGameId(title);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }
    @GET
    @Path("/average")
    @Produces({"application/JSON"})
    public Response getAverageOfGame(@QueryParam("title") String gameTitle) {
        try {
            float result = dal.getAverageOfGame(gameTitle);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/one")
    @Produces({"application/JSON"})
    public Response findByGameIdAndUserId(@QueryParam("title") String gameTitle, @QueryParam("user") String username) {
        try {
            System.out.println("Controller" + gameTitle + " " + username);
            Rating result = dal.findByGameIdAndUserId(gameTitle, username);
            return Response.ok(new RatingModel(result)).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/new")
    @Consumes({"application/JSON"})
    public Response createNewRating(RatingModel rating) {
        try {
            dal.addNewRating(rating);
            return Response.ok("Hej").build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }
/*
    @PostMapping("/rating")
    public ResponseEntity<RatingModel> registerNewRating (@RequestBody RatingModel rating){
        RatingModel newRating = ratingService.createNewRating(rating);
        return new ResponseEntity<>(newRating, HttpStatus.OK);
    }
    */
}
