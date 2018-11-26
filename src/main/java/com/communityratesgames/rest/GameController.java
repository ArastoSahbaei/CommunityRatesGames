package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Game;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/game")
public class GameController {

    @Inject
    private DataAccessLocal dal;

    @GET
    @Produces({"application/JSON"})
    public Response showAllGames() {
        try {
            List<Game> result = dal.showAllGames();
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(414).build();
        }
    }

    @GET
    @Path("/title")
    @Produces({"application/JSON"})
    public Response getOneGamebyTitle(@QueryParam("title") String title){
        try {
            Game result = dal.gameByTitle(title);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(414).build();
        }
    }

    @GET
    @Path("/id")
    @Produces({"application/JSON"})
    public Response getOneGamebyId(@QueryParam("id") Long id){
        try {
            Game result = dal.gameById(id);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(414).build();
        }
    }

    @GET
    @Path("/search")
    @Produces({"application/JSON"})
    public Response searchFiveGames(@QueryParam("q") String q){
        try {
            String result = dal.searchFiveGames(q);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(414).build();
        }
    }
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
