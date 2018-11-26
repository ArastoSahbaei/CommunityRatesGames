import { Component, OnInit } from '@angular/core';
import {StorageService} from "../shared/service/storage.service";
import {Game} from "../shared/interface/game";
import {ApiService} from "../shared/service/api.service";

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  styleUrls: ['./game-page.component.css']
})
export class GamePageComponent implements OnInit {
  public game: Game;
  constructor(private storage: StorageService, api:ApiService) { }

  ngOnInit() {
    /**   TODO:
     *    DEN LOKALA VARIABELN (SOM MAN SÖKER PÅ) SKA DET GÖRAS EN FETCH PÅ
     *    DEN GLOBALA VARIABELN SPARAS I BROWSERN OCH MÅSTE DÄRFÖR TAS BORT EFTER VARJE SÖKNING
     */

    console.log(this.storage.getItem('currentGame'));
    this.storage.removeItem('currentGame');
    //TODO Fetch this motherfucking shit. ask beärn.
        //http.get(this.storage.getItem('currentGame'))
  }

}
