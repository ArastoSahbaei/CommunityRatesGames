import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Headers} from "./headers";
import {UrlService} from "./url.service";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient,
              private url:UrlService) { }

  getGames() {
    const headers = new HttpHeaders();

    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    headers.append('observe' , 'response');

    this.httpClient.get("http://localhost:8080/games", {headers: headers});

  }

  getRating() {
    this.httpClient.get(this.url.getBaseUrl() + this.url.getRating(), {headers: Headers.HeaderJSON() } )
  }

}
