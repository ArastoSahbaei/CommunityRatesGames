package com.communityratesgames.game;

import com.communityratesgames.company.CompanyModel;
import com.communityratesgames.company.CompanyRepository;
import com.communityratesgames.platform.PlatformEntity;
import com.communityratesgames.platform.PlatformModel;
import com.communityratesgames.platform.PlatformRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.communityratesgames.rating.RatingRepository;

@Service
public class GameService implements GameServiceInterface {

    private final GameRepository gameRepository;
    private final RatingRepository ratingRepository;
    private final PlatformRepository platformRepository;
    private final CompanyRepository companyRepository;

    public GameService(
            GameRepository gameRepository,
            RatingRepository ratingRepository,
            PlatformRepository platformRepository,
            CompanyRepository companyRepository)
    {
        this.gameRepository = gameRepository;
        this.ratingRepository = ratingRepository;
        this.platformRepository = platformRepository;
        this.companyRepository = companyRepository;
    }

    private List<GameModel> convertEntityListToModelList(List<GameEntity> list) {
        List<GameModel> out = new ArrayList<>();
        for (GameEntity entity : list) {
            out.add(new GameModel(entity));
        }
        return out;
    }

    @Override
    public GameModel createGame(NewGameModel inputGame) {
        System.out.println(inputGame.toString());
        List<PlatformModel> allPlatforms = inputGame.getAllPlatformId()
                .stream().map(
                        platform -> new PlatformModel(platformRepository.findById(platform))
                ).collect(Collectors.toList());
        CompanyModel company = new CompanyModel(companyRepository.findCompanyById(inputGame.getCompanyId()));
        GameModel newGame = new GameModel(
                inputGame.getTitle(),
                company,
                allPlatforms
        );
        return new GameModel(gameRepository.save(new GameEntity(newGame)));
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

    public List<Map<String, Object>> getTopRatedGames(Integer limit, Integer page) {
        PageRequest request = PageRequest.of(page-1, limit);
        List<Map<String, Object>> items = gameRepository.getTopRatedGames(request);
        return items;
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
}
