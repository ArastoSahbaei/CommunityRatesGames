import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../shared/service/api.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  styleUrls: ['./game-page.component.css']
})
export class GamePageComponent implements OnInit {

  response : any = {};


  constructor(private route: ActivatedRoute, private api: ApiService) {
    this.getTheGames();
  }

  getTheGames(){
    this.route.queryParams.subscribe(queryParam => {
      this.api.getOneGameByTitle(queryParam.title).subscribe(response =>{
        console.log(response);
        this.response = response;
      });
    });
  }

  ngOnInit() {


  }
}

