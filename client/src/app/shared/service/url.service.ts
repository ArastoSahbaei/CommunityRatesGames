import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  //private strings with base url and extensions
  private baseUrl: string = "http://localhost:8080/communityratesgames";
  private loggingUrl: string = "http://localhost:8080/communityratesgameslogging";
  private rating: string = "/rating";
  private new: string = "/new";
  private averageRatingByTitle: string = "/rating/average?title=";
  private top100: string = "/top100";
  private games: string = "/game";
  private user: string = "/user";
  private certainUser: string = "/certainUser";
  private login: string = "/login";
  private register: string = "/register";
  private logout: string = "/logout";
  private update: string = "/update";
  private delete: string = "/delete";
  private credential: string = "/credential";
  private searchGameByTitle: string = "/game/search?q=";
  private oneGamebyTitle: string = "/title";
  private searchGame: string = "/search";
  private company: string = "/company";
  private logs: string = "/logs";
  private statistic: string = "/statistic";
  private onOneUser: string = "OnOneUser";
  private unverifiedGame: string = "/unverifiedgame/create";
  private adminContact: string = "/admincontact";
  private adminAll: string ="/adminall";

  constructor() {
  }

  getCertainUser() {
    return this.certainUser;
  }

  getUpdate() {
    return this.update;
  }

  getDelete() {
    return this.delete;
  }

  getAdminContact() {
    return this.adminContact;
  }

  getNew() {
    return this.new;
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

  getAverageRatingByTitle() {
    return this.averageRatingByTitle;
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

  getLogout() {
    return this.logout;
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

  getUnverifiedGame(){
    return this.unverifiedGame;
  }

  getOnOneUser() {
    return this.onOneUser;
  }

  getAdminAll() {
    return this.adminAll;
  }
}
