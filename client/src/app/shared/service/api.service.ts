import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Headers } from "./headers";
import { UrlService } from "./url.service";
import { Register } from "../interface/register.interface";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient,
              private url:UrlService) { }


  getGames() {
    this.httpClient.get(this.url.getBaseUrl()+this.url.getGames(), {headers: Headers.HeaderJSON()});
  }

  getRating() {
    this.httpClient.get(this.url.getBaseUrl() + this.url.getRating(), {headers: Headers.HeaderJSON() } )
  }

  checkCredentials() {
    this.httpClient.post(this.url.getBaseUrl() + this.url.getLogin(), {}, {headers: Headers.HeaderJSON() } );
  }

  registerUser(body: Register) {
    console.log(body);
  }

}
