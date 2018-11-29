import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  //private strings with base url and extensions
  private baseUrl: string = "http://localhost:8080/communityratesgames";
  private rating: string = "/rating";
  private games: string = "/game";
  private login: string = "/login";
  private register: string = "/register";
  private user: string = "/user";
  private credential: string = "/credential";
  private searchGameByTitle: string = "/game/title?q=halo"

  constructor() {
  }

  getRegister() {
    return this.register;
  }

  getSearchGameByTitle() {
    return this.searchGameByTitle;
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

  getUser() {
    return this.user;
  }
}






