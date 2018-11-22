package com.communityratesgames.game;

import com.communityratesgames.company.CompanyModel;
import com.communityratesgames.company.CompanyRepository;
import com.communityratesgames.platform.PlatformEntity;
import com.communityratesgames.platform.PlatformModel;
import com.communityratesgames.platform.PlatformRepository;
import org.apache.taglibs.standard.tag.el.sql.SetDataSourceTag;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
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
        GameModel newGame = new GameModel(
                inputGame.getTitle(),
                findCompanyAsModel(inputGame.getCompanyId()),
                findAllPlatformsAsModel(inputGame.getAllPlatformId())
        );
        return new GameModel(gameRepository.save(new GameEntity(newGame)));
    }
    private List<PlatformModel> findAllPlatformsAsModel(List<Long> id){
        return platformRepository.findByIdIn(id)
                .stream().map(
                        PlatformModel::new
                ).collect(Collectors.toList());
    }
    private CompanyModel findCompanyAsModel(Long id){
        return new CompanyModel(companyRepository.findCompanyById(id));
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
        return gameRepository.getTopRatedGames(request);
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
