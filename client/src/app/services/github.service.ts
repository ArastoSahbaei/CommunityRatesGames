import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { GithubResponse, Items } from '../shared/interface/item.interface';

@Injectable({
  providedIn: 'root'
})
export class GithubService {
  constructor(private http: HttpClient) {}

  search(query: string): Observable<GithubResponse> {
    const url = 'http://localhost:8080/api/game/search';
    return this.http
      .get<GithubResponse>(url, {
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
