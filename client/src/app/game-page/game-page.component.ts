import { Component, OnInit } from '@angular/core';
import {StorageService} from "../shared/service/storage.service";
import {Game} from "../shared/interface/game";

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  styleUrls: ['./game-page.component.css']
})
export class GamePageComponent implements OnInit {
  public game: Game;
  constructor(private storage: StorageService) { }

  ngOnInit() {
    //TODO Fetch this motherfucking shit. ask beärn.
        //http.get(this.storage.getItem('currentGame'))
  }

}
