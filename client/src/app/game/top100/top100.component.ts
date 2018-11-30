import { Component, OnInit } from '@angular/core';
import {TopGames} from "../../shared/interface/top-games.interface";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../shared/service/api.service";

@Component({
  selector: 'app-top100',
  templateUrl: './top100.component.html',
  styleUrls: ['./top100.component.css']
})
export class Top100Component implements OnInit {

  topGames: FormGroup;

  constructor(private api: ApiService , private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.topGames = this.formBuilder.group({
      'title': ['', [Validators.required]],
      'companyId': ['', Validators.required],
      'allPlatformId': ['', Validators.required]
    });
  }

  getTopGames() {
    const topGames = {} as TopGames;
    topGames.title = this.topGames.value.title;
    topGames.company = this.topGames.value.company;
    topGames.platform = this.topGames.value.platform;
    //topGames.platform = [];

    this.api.getTop100().subscribe((response) =>{
      console.log(response);

    });

  }

  games: TopGames[] = [
    {
      title: 'God Of War',
      platform: 'PS4',
      company: 'Activision',
      updated: new Date('2/5/17'),
    },
    {
      title: 'FIFA 19',
      platform: 'PS4, Xbox One',
      company: 'Ubisoft',
      updated: new Date('8/8/18'),
    },
    {
      title: 'Arasto da bear',
      platform: 'imagination',
      company: 'skogsmullarna',
      updated: new Date('1/1/19'),
    },

    {
      title: 'Halo',
      platform: 'Xbox 360',
      company: 'Bungie',
      updated: new Date('10/11/12'),
    },

    {
      title: 'Diablo',
      platform: 'PS4, Xbox One, PC',
      company: 'Blizzard Entertainment',
      updated: new Date('10/1/10'),
    },

    {
      title: 'World Of Warcraft',
      platform: 'PC',
      company: 'Blizzard Entertainment',
      updated: new Date('10/1/04'),
    },


  ];

  //Här skall det finnas get metoder.

}
