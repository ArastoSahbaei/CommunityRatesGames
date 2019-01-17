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

  constructor(private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.addGames = this.formBuilder.group({
      'title': ['', [Validators.required]],
      'company': ['', Validators.required],
      'releaseYear': ['', Validators.required]
    });
  }

  addGame(){
    let game = {} as AddGame;
    game.title = this.addGames.value.title;
    game.company = this.addGames.value.company;
    game.releaseYear = this.addGames.value.releaseYear;

    console.log(game);

    this.api.postGame(game).subscribe((response) =>{
      console.log(response);
    });

  }

  get title(){
    return this.addGames.get('title')
  }

  get company(){
    return this.addGames.get('company')
  }

  get game(){
    return this.addGames.get('game')
  }

  get releaseYear(){
    return this.addGames.get('releaseYear')
  }
}
