import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AddGame} from "../../shared/interface/add-game.interface";
import {ApiService} from "../../shared/service/api.service";


@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrls: ['./add-game.component.css']
})
export class AddGameComponent implements OnInit {

  addGames : FormGroup;
  ratings : FormGroup;

  max: number = 10;
  rate: number = 2;
  star: number;


  constructor(private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.addGames = this.formBuilder.group({
      'title': ['', [Validators.required]],
      'companyId': ['', Validators.required],
     'allPlatformId': ['', Validators.required]
    });

    this.ratings = this.formBuilder.group({
      'user': ['',[Validators.required]],
      'game': ['', Validators.required],
      'rating': [ , Validators.required]
    });
  }

  addGame(){
    const game = {} as AddGame;
    game.title = this.addGames.value.title;
    game.companyId = this.addGames.value.companyId;
    game.allPlatformId = [];

    this.api.postGame(game).subscribe((response) =>{
      console.log(response);

    });

  }

  addRating(star: number){
    const rate = {} as AddGame;
    rate.user = this.ratings.value.user;
    rate.game = this.ratings.value.game;
 //   rate.rating = this.ratings.value.rating;
    rate.rating = this.star;
    console.log(rate)

    this.api.postRating(rate).subscribe((response) =>{
      console.log(response);


    });

  }

  hoveringOver(value: number): void {
    this.star = value;
    console.log(this.star)
    this.addRating(this.star);
  }

  get title(){
    return this.addGames.get('title')
  }

  get companyId(){
    return this.addGames.get('companyId')
  }

  get allPlatformId(){
    return this.addGames.get('allPlatformId')
  }

  get user(){
    return this.addGames.get('user')
  }

  get game(){
    return this.addGames.get('game')
  }

  get rating(){
    return this.addGames.get('rating')
  }
}
