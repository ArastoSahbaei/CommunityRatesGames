package com.communityratesgames.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<GameModel>> getGames() {
        List<GameModel> games = gameService.findAllGames();
        return new ResponseEntity(games, HttpStatus.OK);
    }

    //TODO: Security And/Or userrequired
    @PostMapping("/game")
    public ResponseEntity<List<GameModel>> createGame(@RequestBody GameModel gameModel) {
        GameModel newGameModel = gameService.createGame(gameModel);
        return new ResponseEntity(newGameModel, HttpStatus.OK);
    }

    @GetMapping("/game/search/{searchString}")
    public ResponseEntity<List<String>> searchGame(@PathVariable String searchString) {
        return new ResponseEntity(gameService.searchGame(searchString),HttpStatus.OK);
    }

}
