package com.communityratesgames.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private final GameService gameService;

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
    public ResponseEntity<List<GameModel>> createGame(@RequestBody GameModel gameModel) {
        GameModel newGameModel = gameService.createGame(gameModel);
        return new ResponseEntity(newGameModel, HttpStatus.OK);
    }

    @GetMapping("/game/search")
    public ResponseEntity<List<HashMap<String,Object>>> searchGame(@RequestParam("q") String searchString) {
        List<HashMap<String,Object>> games = gameService.searchForFiveGames(searchString);
        return new ResponseEntity<>(games,HttpStatus.OK);
    }
}
