import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Headers} from "./headers";
import {UrlService} from "./url.service";
import {AddGame} from "../interface/add-game.interface";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient,
              private url:UrlService) { }


// normal get / post / put / delete method, nasty way, lots of code to be changed
  getGames() {
    const headers = new HttpHeaders();

    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    headers.append('observe' , 'response');

    this.httpClient.get("http://localhost:8080/games", {headers: headers});

  }

//  Cleaner way

  getRating() {
    this.httpClient.get(this.url.getBaseUrl() + this.url.getRating(), {headers: Headers.HeaderJSON() } )
  }

  checkCredentials() {
    this.httpClient.post(this.url.getBaseUrl() + this.url.getLogin(), {}, {headers: Headers.HeaderJSON() } );
  }

  postGame(body: AddGame){
    console.log(body);
  return this.httpClient.post(this.url.getBaseUrl()+ this.url.getGames(), body, {headers: Headers.HeaderJSON()}
    );
  }

}
