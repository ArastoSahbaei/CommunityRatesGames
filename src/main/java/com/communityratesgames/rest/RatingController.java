package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Rating;
import com.communityratesgames.model.RatingModel;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/rating")
public class RatingController {

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
    public Response findRatingsByGameId(@QueryParam("id") Long gameId) {
        try {
            List<Rating> result = dal.findRatingsByGameId(gameId);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }
    @GET
    @Path("/average")
    @Produces({"application/JSON"})
    public Response getAverageOfGame(@QueryParam("id") Long gameId) {
        try {
            float result = dal.getAverageOfGame(gameId);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/one")
    @Produces({"application/JSON"})
    public Response findByGameIdAndUserId(@QueryParam("gameid") Long gameId, @QueryParam("userid") Long userId) {
        try {
            Rating result = dal.findByGameIdAndUserId(gameId, userId);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/new")
    @Consumes({"application/JSON"})
    public Response createNewRating(RatingModel rating) {
        try {
            dal.addNewRating(new Rating(rating));
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
