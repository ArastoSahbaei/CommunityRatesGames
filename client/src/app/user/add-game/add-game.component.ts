import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AddGame} from "../../shared/interface/add-game.interface";
import {ApiService} from "../../shared/service/api.service";
import {StorageService} from "../../shared/service/storage.service";


@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrls: ['./add-game.component.css']
})
export class AddGameComponent implements OnInit {

  addGames : FormGroup;

  constructor(private api: ApiService, private formBuilder: FormBuilder, private storage: StorageService) { }

  ngOnInit() {
    this.addGames = this.formBuilder.group({
      'title': ['', [Validators.required]],
      'company': ['', Validators.required],
      'releaseDate': ['', Validators.required],
      'platforms': ['',Validators.required],
      'description': ['', Validators.required],
      'genre': ['', Validators.required]

    });
  }

  addGame(){
    let game = {} as AddGame;
    game.platforms = [];
    game.title = this.addGames.value.title;
    game.company = this.addGames.value.company;
    game.releaseDate = this.addGames.value.releaseDate;
    game.platforms.push(this.addGames.value.platforms);
    game.genre = this.addGames.value.genre;
    game.description = this.addGames.value.description;
    game.submittedBy = this.storage.getItem('name');

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

  get releaseDate(){
    return this.addGames.get('releaseDate')
  }

  get platforms(){
    return this.addGames.get('platforms')
  }

  get genre(){
    return this.addGames.get('genre');
  }

  get description(){
    return this.addGames.get('description')
  }

  get username(){
    return this.addGames.get('username');
  }
}
