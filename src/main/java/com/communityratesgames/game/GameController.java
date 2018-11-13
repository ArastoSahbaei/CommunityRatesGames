package com.communityratesgames.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        return new ResponseEntity(game, HttpStatus.OK);
    }

    //TODO: Security And/Or userrequired
    @PostMapping("/game")
    public ResponseEntity<List<GameModel>> createGame(@RequestBody GameModel gameModel) {
        GameModel newGameModel = gameService.createGame(gameModel);
        return new ResponseEntity(newGameModel, HttpStatus.OK);
    }

    @GetMapping("/game/search")
    public ResponseEntity<List<SearchReply>> searchGame(@RequestParam("q") String searchString) {
        List<GameModel> games = gameService.searchGame(searchString);
        List<SearchReply> out = new ArrayList();
        for (GameModel game : games) {
            out.add(new SearchReply(game));
        }
        return new ResponseEntity(out,HttpStatus.OK);
    }

}

class SearchReply {
    public Long id;
    public String title;

    public SearchReply(GameModel entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
