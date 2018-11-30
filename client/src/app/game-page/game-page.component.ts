import { Component, OnInit } from '@angular/core';
import {StorageService} from "../shared/service/storage.service";
import {Game} from "../shared/interface/game";
import {ApiService} from "../shared/service/api.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  styleUrls: ['./game-page.component.css']
})
export class GamePageComponent implements OnInit {
  public games = this.storage.getItem('currentGame');
  constructor(private storage: StorageService, private api: ApiService) { }

  getData() {
     this.api.searchGameByTitle().subscribe((data)=> console.log(data));
  }



  ngOnInit() {
    /**   TODO:
     *  1. CREATE GET/FETCH
     *  2. UPDATE THE FETCH TO LOCALHOST
     *  3. JSON SHOULD BE RETRIEVED USING THE GLOBAL VARIABLE (FROM THE SEARCH FIELD)
     *  4. GLOBAL VARIABLE SHOULD BE REMOVED AFTER EVERY UPDATE(?) IF SAVED IN BROWSERB
     */

  this.getData();
    console.log(this.storage.getItem('currentGame'));
    console.log(this.storage.getItem('currentGame'));
    console.log(this.storage.getItem('currentGame'));


    this.storage.removeItem('currentGame');

  }

}
