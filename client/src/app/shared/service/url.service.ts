import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  //private strings with base url and extensions
  private baseUrl: string = "http://localhost:8080/api";
  private rating: string = "/rating";
  private games: string = "/games";
  private login: string = "/login";
  private user: string = "/user";
  private credential: string = "/credential";

  constructor() { }

  getBaseUrl() {
    return this.baseUrl;
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

  getCredential() {
    return this.credential;
  }
}
