package com.communityratesgames.model;

import java.io.Serializable;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.List;
import com.communityratesgames.domain.Game;
import lombok.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class GameModel implements Serializable {
    private Long id;
    private Timestamp releaseDate;
    private String title;
    private CompanyModel company;
    private List<PlatformModel> platforms;
    private boolean verified;
    private float averageRating;

    private JsonObject jsonFromString(String input) {
        JsonReader jsonReader = Json.createReader(new StringReader(input));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        return object;
    }

    public Game toEntity(String input) {
        JsonObject json = jsonFromString(input);
        Game game = new Game();

        title = json.getString("title");

        game.setTitle(title);

        return game;
    }

    public GameModel toModel(Game game) {
        GameModel gm = new GameModel();

        gm.title = game.getTitle();
        gm.company = game.getCompany();
        gm.platforms = game.getPlatforms();
        gm.verified = game.isVerified();

        return gm;
    }

    public GameModel(Game game) {
        this.title = game.getTitle();
        this.company = game.getCompany();
        this.platforms = game.getPlatforms();
        this.verified = false;
       // this.averageRating = game.getAverageRating();
    }
}


/*


    public GameModel(String title, CompanyModel company, List<PlatformModel> platforms) {
        this.title = title;
        this.company = company;
        this.platforms = platforms;
        this.verified = false;
    }

    public GameModel(Game game) {
        this(game, 0.0f);
    }

    public GameModel(Game game, float average) {
        this.id = game.getId();
        this.releaseDate = game.getReleaseDate();
        this.title = game.getTitle();
        this.company = new CompanyModel(game.getCompany());
        this.platforms = new ArrayList<PlatformModel>();
        for (Platform platform : game.getPlatforms()) {
            this.platforms.add(new PlatformModel(platform));
        }
        this.verified = true;
        this.averageRating = average;
    }

    protected GameModel() { }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public CompanyModel getCompany() {
        return this.company;
    }

    public List<PlatformModel> getPlatforms() {
        return this.platforms;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

    public void setPlatforms(List<PlatformModel> platforms) {
        this.platforms = platforms;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public float getAverageRating() {
        return this.averageRating;
    }

    public void setAverageRating(float average) {
        this.averageRating = average;
    }
}
*/