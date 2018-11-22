import {Component, OnInit} from '@angular/core';
import {catchError, debounceTime, map, startWith, switchMap} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {FormControl} from "@angular/forms";
import {SearchgameService} from "../shared/service/searchgame.service";
import {SearchGames} from "../shared/interface/search-game.interface";

@Component({
  selector: 'app-searchgame',
  templateUrl: './searchgame.component.html',
  styleUrls: ['./searchgame.component.css']
})
export class SearchgameComponent implements OnInit {
  public searchGameAutoComplete$: Observable<SearchGames> = null;
  public autoCompleteControl = new FormControl();

  constructor(private searchGameService: SearchgameService) {
  }

  lookup(value: string): Observable<SearchGames> {
    return this.searchGameService.search(value.toLowerCase()).pipe(
      // map the item property of the backend results as our return object
      // map(results => results.items),
      // catch errors
      catchError(_ => {
        return of(null);
      })
    );
  }

  ngOnInit() {
    this.searchGameAutoComplete$ = this.autoCompleteControl.valueChanges.pipe(
      startWith(''),
      // delay emits
      debounceTime(300),
      // use switch map so as to cancel previous subscribed events, before creating new once
      switchMap(value => {
        if (value !== '') {
          // lookup from github
          return this.lookup(value);
        } else {
          // if no value is present, return null
          return of(null);
        }
      })
    );
  }

}
