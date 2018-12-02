import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../shared/service/storage.service";
import {ApiService} from "../../shared/service/api.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  styleUrls: ['./game-page.component.css']
})
export class GamePageComponent implements OnInit {
  public games;

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(queryParam => {
      this.games = queryParam.title;
    });

    this.route.queryParams.subscribe(routeParams => {

    });
  }

}
