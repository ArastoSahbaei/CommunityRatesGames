import { Component, OnInit } from '@angular/core';
import { FormControl } from "@angular/forms";
import { SearchgameService } from "../../shared/service/searchgame.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-searchgame',
  templateUrl: './searchgame.component.html',
  styleUrls: ['./searchgame.component.css'],
  providers:[ SearchgameService ]
})
export class SearchgameComponent implements OnInit {

  public search: FormControl = new FormControl();
  public games = <object>[];

  constructor(private searchGameService: SearchgameService,
              private router: Router) {}

  ngOnInit() {
    this.search.valueChanges.subscribe(
      game => {
        if ( game != '' ) {
          this.searchGameService.searchGame(game).subscribe(
            data => {
              this.games = data as any[];
            })
        }
      });
    this.search.setValue('');
  }

  selectedGame(event) {
    this.router.navigate(['/game/gamepage'], {queryParams: {title: event}});
  }
}
