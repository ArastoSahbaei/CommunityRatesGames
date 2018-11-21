import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SearchGameResponse} from "../interface/item.interface";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SearchgameService {

  constructor(private http: HttpClient) {
  }

  search(query: string): Observable<SearchGameResponse> {
    const url = 'http://localhost:8080/api/game/search';
    return this.http
      .get<SearchGameResponse>(url, {
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
