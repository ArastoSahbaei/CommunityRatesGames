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
  image : string = "";
  ratingData : any;

  constructor(private route: ActivatedRoute, private api: ApiService) {
    this.getTheGames();
  }

  getTheGames(){
    this.route.queryParams.subscribe(queryParam => {
      this.api.getOneGameByTitle(queryParam.title).subscribe(response =>{
        console.log(response);
        console.log(Object.values(response)[2]);
        this.showImage(response);
        this.response = response;
        this.getAverageRating(Object.values(response)[2]);
      });
    });
  }

  getAverageRating(image:string) {
    console.log(image);
    this.api.getAverageRatingByTitle(image).subscribe(ratingData =>{
      console.log(ratingData);
      this.ratingData = ratingData;
    });
  }

  showImage(response) {
    this.image = Object.values(response)[2].toString().replace(/\s/g, "").toLowerCase();
  }

  ngOnInit() {

  }
}

