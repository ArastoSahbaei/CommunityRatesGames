import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  player: YT.Player;
  private id: string = "sJWuMwz31qE";

  savePlayer(player){
    this.player = player;

    console.log('player instance', player);
  }

  onStateChange(event){
    console.log('player state', event.data);
  }

  constructor() { }

  ngOnInit() {
  }

}
