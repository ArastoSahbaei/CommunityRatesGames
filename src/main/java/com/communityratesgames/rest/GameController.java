package com.communityratesgames.rest;

public class GameController {
/*
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

    //TODO: Security And/Or userrequired
    @PostMapping("/game")
    public ResponseEntity<GameModel> createGame(@RequestBody GameModel gameModel) {
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
    }*/
}
