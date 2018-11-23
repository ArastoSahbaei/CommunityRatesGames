import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Headers } from "./headers";
import { UrlService } from "./url.service";
import { Register } from "../interface/register.interface";
import {User} from "../interface/user.interface";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient,
              private url:UrlService) { }


  getGames() {
    return this.httpClient.get(this.url.getBaseUrl()+this.url.getGames(), {headers: Headers.HeaderJSON()});
  }

  getRating() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getRating(), {headers: Headers.HeaderJSON() } )
  }

  checkCredentials(body: User) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getCredential(), body, {headers: Headers.HeaderJSON() } );
  }

  registerUser(body: Register) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getUser(), body, {headers: Headers.HeaderJSON() } );
  }

}
