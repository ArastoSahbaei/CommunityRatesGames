import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { GithubResponse, Items } from '../interface';

@Injectable({
  providedIn: 'root'
})
export class GithubService {
  constructor(private http: HttpClient) {}

  search(query: string): Observable<GithubResponse> {
    const url = 'localhost:8080/api/user';
    return this.http
      .get<GithubResponse>(url, {
        observe: 'response',
        params: {
          q: query,
          sort: 'stars',
          order: 'desc'
        }
      })
      .pipe(
        map(res => {
          return res.body;
        })
      );
  }
}
