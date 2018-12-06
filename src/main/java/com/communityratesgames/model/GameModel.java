package com.communityratesgames.model;

import java.io.Serializable;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.communityratesgames.domain.Game;
import com.communityratesgames.domain.Platform;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.json.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameModel implements Serializable {

    private Long id;
    private Timestamp releaseDate;
    private String title;
    private String company;
    private List<String> platforms;
    private boolean verified;
    private float averageRating;


    private JsonObject jsonFromString(String input) {
        JsonReader jsonReader = Json.createReader(new StringReader(input));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object;
    }


    public GameModel test(String input) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> list = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(input);
            GameModel gameModel = new GameModel();
            gameModel.setReleaseDate(Timestamp.valueOf(root.get("releaseDate").asText()));
            gameModel.setTitle(root.get("title").asText());
            gameModel.setCompany(root.get("company").asText());
            JsonNode jn = root.get("platforms");
            for (JsonNode n : jn
                 ) {
                list.add(n.asText());
            }
            gameModel.setPlatforms(list);


         //   gameModel.setCompany(json.getString("company"));
           // gameModel.setPlatforms(herp);
            return gameModel;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }




        /*
        GameModel gameModel = new GameModel();
        JsonObject json = jsonFromString(input);
        ArrayList<String> herp = new ArrayList<>();

        JsonArray derp = json.getJsonArray("platforms");
        for (JsonValue v : derp
        ) {
            herp.add(v.toString());
        }
*/
    }





            public GameModel(Game game) {
            this.title = game.getTitle();
            this.company = game.getCompany().getCompanyName();
            this.platforms = game.getPlatforms().stream().map(Platform::getName).collect(Collectors.toList());
            this.verified = game.isVerified();
            this.averageRating = game.getAverageRating();
        }
    }


/*
        //gameModel.setReleaseDate(Timestamp.valueOf(json.getString("releaseDate")));
        gameModel.setTitle(json.getString("title"));
        gameModel.setCompany(json.getString("company"));
        gameModel.setPlatforms(herp);
        return gameModel;
    }
*/
/*
    public GameModel toModel(String input) {
        GameModel gm = new GameModel();

        gm.title = game.getTitle();
       // gm.company = game.getCompany();
       // gm.platforms = game.getPlatforms();
        gm.verified = game.isVerified();
       // gm.averageRating = game.getAverageRating??

        return gm;
    }
*/

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