import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  //private strings with base url and extensions
  private baseUrl: string = "http://localhost:8080/communityratesgames";
  private mongoUrl: string ="http://localhost:4000/message/add";
  private loggingUrl: string = "http://localhost:8080/communityratesgameslogging";
  private rating: string = "/rating/new";
  private top100: string = "/toprated";
  private games: string = "/game";
  private login: string = "/login";
  private register: string = "/register";
  private user: string = "/user";
  private credential: string = "/credential";
  private searchGameByTitle: string = "/game/search?q=";
  private oneGamebyTitle: string = "/title";
  private searchGame: string = "/search";
  private company: string = "/company";
  private logs: string = "/logs";
  private statistic: string = "/statistic";

  constructor() {
  }

  getMongoUrl() {
    return this.mongoUrl;
  }

  getCompany() {
    return this.company;
  }

  getRegister() {
    return this.register;
  }

  getSearch() {
    return this.searchGame;
  }

  getSearchGameByTitle() {
    return this.searchGameByTitle;
  }

  getTop100(){
    return this.top100;
  }

  getBaseUrl() {
    return this.baseUrl;
  }

  getCredential() {
    return this.credential;
  }

  getStatistic() {
    return this.statistic;
  }

  getRating() {
    return this.rating;
  }

  getGames() {
    return this.games;
  }

  getOneGamebyTitle(){
    return this.oneGamebyTitle;
}

  getLogin() {
    return this.login;
  }

  getUser() {
    return this.user;
  }

  getLogs() {
    return this.logs;
  }

  getBaseUrlLogs() {
    return this.loggingUrl;
  }
}






