package com.communityratesgames.game;

import com.fasterxml.jackson.datatype.jdk8.StreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game")
    public ResponseEntity<GameModel> getGameById(@RequestParam("id") Long id) {
        GameModel game = gameService.findGameById(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    //TODO: Security And/Or userrequired
    @PostMapping("/game")
    public ResponseEntity<GameModel> createGame(@RequestBody GameModel gameModel) {
        GameModel newGameModel = gameService.createGame(gameModel);
        return new ResponseEntity<>(newGameModel, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/shitface",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<String> streamAllGames() {
        try {
            return new ResponseEntity<>(gameService.streamAllGames(), HttpStatus.OK);
        }
        catch (IOException e){
            System.out.println("SHIT WENT BAD");
            return new ResponseEntity<>("Fubar", HttpStatus.OK);
        }

    }


    @GetMapping("/game/search")
    public ResponseEntity<List<HashMap<String,Object>>> searchGame(@RequestParam("q") String searchString) {
        List<HashMap<String,Object>> games = gameService.searchForFiveGames(searchString);
        return new ResponseEntity<>(games,HttpStatus.OK);
    }
}
