import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {debounceTime, map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SearchgameService {

  private allGames;

  constructor(private api: ApiService) {}

  searchGame(game : any) {
    this.allGames = this.api.searchGame(game).pipe(
      debounceTime(500),
      map(
        ( response: any ) => {
          return ( response.length != 0 ? response as any[] : [{"title": "No games found"} as any] );
        }
      )
    );

    return this.allGames;
  }
}
