import {AddGame} from "../interface/add-game.interface";
import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Headers} from "./headers";
import {UrlService} from "./url.service";
import {Register} from "../interface/register.interface";
import {User} from "../interface/user.interface";
import {StorageService} from "./storage.service";


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient,
              private url: UrlService, private storage: StorageService) {
  }


  getGames() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getGames(), {headers: Headers.HeaderJSON()});
  }

  searchGameByTitle() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getSearchGameByTitle() + this.storage.getItem('currentGame'), {headers: Headers.HeaderJSON()});
  }

  getRating() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getRating(), {headers: Headers.HeaderJSON()})
  }

  checkCredentials(body: User) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getUser() + this.url.getLogin(), body, {headers: Headers.HeaderJSON()});
  }

  registerUser(body: Register) {
    console.log(body);
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getUser() + this.url.getRegister(), body, {headers: Headers.HeaderJSON()});
  }

  postGame(body: AddGame) {
    console.log(body);
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getGames(), body, {headers: Headers.HeaderJSON()}
    );
  }

}








