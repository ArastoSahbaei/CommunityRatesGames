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
      'companyId': ['', Validators.required],
     'allPlatformId': ['', Validators.required]
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

  get title(){
    return this.addGames.get('title')
  }

  get companyId(){
    return this.addGames.get('companyId')
  }

  get allPlatformId(){
    return this.addGames.get('allPlatformId')
  }
}
