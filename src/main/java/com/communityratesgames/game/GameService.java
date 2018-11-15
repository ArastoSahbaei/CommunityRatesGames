package com.communityratesgames.game;

import com.communityratesgames.platform.PlatformEntity;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.communityratesgames.rating.RatingRepository;

@Service
public class GameService implements GameServiceInterface {

    private final GameRepository gameRepository;
    private final RatingRepository ratingRepository;

    public GameService(GameRepository gameRepository, RatingRepository ratingRepository) {
        this.gameRepository = gameRepository;
        this.ratingRepository = ratingRepository;
    }

    private List<GameModel> convertEntityListToModelList(List<GameEntity> list) {
        List<GameModel> out = new ArrayList<>();
        for (GameEntity entity : list) {
            out.add(new GameModel(entity));
        }
        return out;
    }

    @Override
    public GameModel createGame(GameModel gameModel) {
        GameEntity gameEntity = new GameEntity(gameModel);
        return new GameModel(gameRepository.save(gameEntity));
    }
    public List<HashMap<String,Object>> searchForFiveGames(String searchString){
        return gameRepository.findFirst5ByTitleContaining(searchString, Sort.unsorted()
        ).stream().map(this::reduceGameToIdAndTitle).collect(Collectors.toList());
    }

    private HashMap<String, Object> reduceGameToIdAndTitle(GameEntity game){
        HashMap<String, Object> reducedGame = new HashMap<>();
        reducedGame.put("id", game.getId());
        reducedGame.put("title", game.getTitle());
        return reducedGame;
    }

    @Override
    public List<GameModel> findAllGames() {
        return convertEntityListToModelList(gameRepository.findAll());
    }

    public GameModel findGameById(Long id) {
        GameEntity gameEntity = gameRepository.findGameById(id);
        Float average = ratingRepository.getGameAverageRating(gameEntity.getId());
        return new GameModel(gameEntity, average);
    }

    @Override
    public GameEntity findGameByTitle(String title) {
        return null;
    }

    public String streamAllGames() throws IOException {
        List<GameEntity> games = gameRepository.findAll();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonFactory jfactory = new JsonFactory();
        JsonGenerator jGenerator = jfactory
                .createGenerator(stream, JsonEncoding.UTF8);

        jGenerator.writeStartArray();
        for (GameEntity game:games
             ) {
            jGenerator.writeStartObject();
            jGenerator.writeNumberField("id", game.getId());
            jGenerator.writeStringField("title", game.getTitle());
            jGenerator.writeFieldName("platform");
            jGenerator.writeStartArray();
            for (PlatformEntity platform:game.getPlatforms()
                 ) {
                jGenerator.writeString(platform.getName());
            }
            jGenerator.writeEndArray();
            jGenerator.writeStringField("company", game.getCompany().getCompanyName());
            jGenerator.writeEndObject();
        }
        jGenerator.writeEndArray();
        jGenerator.close();
        return new String(stream.toByteArray(), StandardCharsets.UTF_8);
    }
}
