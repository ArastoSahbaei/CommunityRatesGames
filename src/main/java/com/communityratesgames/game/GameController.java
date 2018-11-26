package com.communityratesgames.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<GameModel> getGameById(@RequestParam("q") Long id) {
        GameModel game = gameService.findGameById(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/game/title")
    public ResponseEntity<GameModel> getGameByTitle(@RequestParam("q") String title) {
        GameModel game = gameService.findGameByTitle(title);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    //TODO: Security And/Or user required. Probably should not return a GameModel
    @PostMapping("/game")
    public ResponseEntity<GameModel> createGame(@RequestBody NewGameModel gameModel) {
        GameModel newGameModel = gameService.createGame(gameModel);
        return new ResponseEntity<>(newGameModel, HttpStatus.OK);
    }

    @GetMapping("/game/top")
    public ResponseEntity<List<Map<String,Object>>> getTopRatedGames(
            @RequestParam(value="limit",defaultValue="10") Integer limit,
            @RequestParam(value="page",defaultValue="1") Integer page) {
        List<Map<String,Object>> out = gameService.getTopRatedGames(limit, page);
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @GetMapping("/game/search")
    public ResponseEntity<List<HashMap<String,Object>>> searchGame(@RequestParam("q") String searchString) {
        List<HashMap<String,Object>> games = gameService.searchForFiveGames(searchString);
        return new ResponseEntity<>(games,HttpStatus.OK);
    }
}
