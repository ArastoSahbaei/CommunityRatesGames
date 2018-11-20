import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  //private strings with base url and extensions
  private baseUrl: string = "http://localhost:8080";
  private rating: string = "/rating";
  private games: string = "/games";


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
}
