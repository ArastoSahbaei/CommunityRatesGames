package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Rating;
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
    public Response createNewRating() {

    }
/*
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rating")
    public ResponseEntity<RatingModel> registerNewRating (@RequestBody RatingModel rating){
        RatingModel newRating = ratingService.createNewRating(rating);
        return new ResponseEntity<>(newRating, HttpStatus.OK);
    }

    @GetMapping("/rating")
    public ResponseEntity<List<RatingModel>> getRatings(){
        List<RatingModel> ratingList = ratingService.getAllRatings();
        return new ResponseEntity<>(ratingList, HttpStatus.OK);
    }

    @GetMapping("/rating/list")
    public ResponseEntity<List<RatingModel>> getRatingsByGameId (@RequestParam("gameId") Long gameId){
        List<RatingModel> ratingList = ratingService.findRatingsByGameId(gameId);
        return new ResponseEntity<>(ratingList, HttpStatus.OK);
    }

    @GetMapping("/rating/average")
    public ResponseEntity<Float> getRatingAverageByGameId (@RequestParam("gameId") Long gameId){
        return new ResponseEntity<>(ratingService.getAverageOfGame(gameId), HttpStatus.OK);
    }

    /*
    //TODO: Refactor when needed
    @GetMapping("/rating/{gameid}/{userid}")
    public ResponseEntity<RatingModel> getRatingByGameIdAndUserId(@PathVariable Long gameId, Long userId){
        RatingModel rating = ratingService.findByGameIdAndUserId(gameId, userId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
    */
}
