import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {SearchGameInterface} from "../interface/search-game.interface";

@Injectable({
  providedIn: 'root'
})
export class SearchgameService {

  constructor(private http: HttpClient) {
  }

  search(query: string): Observable<SearchGameInterface> {
    const url = 'http://localhost:8080/api/game/search';
    return this.http
      .get<SearchGameInterface>(url, {
        observe: 'response',
        params: {
          q: query
        }
      })
      .pipe(
        map(res => {
          return res.body;
        })
      );
  }
}
