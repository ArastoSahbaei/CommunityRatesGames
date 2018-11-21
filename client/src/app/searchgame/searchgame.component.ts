import { Component, OnInit } from '@angular/core';
import {catchError, debounceTime, map, startWith, switchMap} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {Items} from "../shared/interface/item.interface";
import {FormControl} from "@angular/forms";
import {SearchgameService} from "../shared/service/searchgame.service";

@Component({
  selector: 'app-searchgame',
  templateUrl: './searchgame.component.html',
  styleUrls: ['./searchgame.component.css']
})
export class SearchgameComponent implements OnInit {
  public githubAutoComplete$: Observable<Items> = null;
  public autoCompleteControl = new FormControl();

  constructor(private searchGameService: SearchgameService) { }
  lookup(value: string): Observable<Items> {
    return this.searchGameService.search(value.toLowerCase()).pipe(
      // map the item property of the github results as our return object
      map(results => results.items),
      // catch errors
      catchError(_ => {
        return of(null);
      })
    );
  }

  ngOnInit() {
    this.githubAutoComplete$ = this.autoCompleteControl.valueChanges.pipe(
      startWith(''),
      // delay emits
      debounceTime(300),
      // use switch map so as to cancel previous subscribed events, before creating new once
      switchMap(value => {
        if (value !== '') {
          // lookup from github
          return this.lookup(value);
        } else {
          // if no value is pressent, return null
          return of(null);
        }
      })
    );
  }

}
