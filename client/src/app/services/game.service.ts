import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class GameService {

  constructor(private http: HttpClient) { }
  configUrl = 'http://localhost:8080/api/game?id=1';

  getConfig() {
    return this.http.get(this.configUrl);
  }
}
