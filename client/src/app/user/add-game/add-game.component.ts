import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrls: ['./add-game.component.css']
})
export class AddGameComponent implements OnInit {

  title: String = '';

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
  }

  postGame(){
    this.httpClient.post('http://localhost:8080/api/company',{
      title: 'Spyro'
    }).subscribe(
      (data:any) => {
        console.log(data);
      }
    )
  }
}
