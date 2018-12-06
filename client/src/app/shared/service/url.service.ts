import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  //private strings with base url and extensions
  private baseUrl: string = "http://localhost:8080/communityratesgames";
  private rating: string = "/rating";
  private top100: string = "/toprated";
  private games: string = "/game";
  private login: string = "/login";
  private logout: string = "/logout?token=";
  private register: string = "/register";
  private user: string = "/user";
  private session: string = "/session";
  private credential: string = "/credential";
  private searchGameByTitle: string = "/game/search?q=";
  private searchGame: string = "/search";
  private company: string = "/company";


  constructor() {
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

  getRating() {
    return this.rating;
  }

  getGames() {
    return this.games;
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

  getSession() {
    return this.session;
  }
}
