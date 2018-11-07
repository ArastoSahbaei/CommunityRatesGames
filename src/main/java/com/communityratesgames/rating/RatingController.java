package com.communityratesgames.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RatingController {
    @Autowired
    private final RatingService ratingService;

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

    /*
    @GetMapping("/rating/{gameid}")
    public ResponseEntity<List<RatingModel>> getRatingsByGame (@PathVariable Long game){
        List<RatingModel> ratingList = ratingService.findRatingsByGame(game);
        return new ResponseEntity<>(ratingList, HttpStatus.OK);
    }
    */

    /*
    //TODO: Refactor when needed
    @GetMapping("/rating/{gameid}/{userid}")
    public ResponseEntity<RatingModel> getRatingByGameIdAndUserId(@PathVariable Long gameId, Long userId){
        RatingModel rating = ratingService.findByGameIdAndUserId(gameId, userId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
    */
}
