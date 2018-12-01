import {Component, OnDestroy, OnInit} from '@angular/core';
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
  public games;

  constructor(private storage: StorageService, private api: ApiService) {
  }

  getData() {
    this.api.searchGameByTitle().subscribe((data) => console.log(data));
    this.games = this.storage.getItem('currentGame');
    console.log("update get data");
  }

  public updateData() {
    this.games = this.storage.getItem('currentGame');
  }

  ngOnInit() {
    this.getData();
    this.storage.removeItem('currentGame');
  }

}
