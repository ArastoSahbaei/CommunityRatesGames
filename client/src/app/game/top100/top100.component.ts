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

  constructor(private api: ApiService , private formBuilder: FormBuilder) {
    this.api.getTop100().subscribe((response) =>{
      console.log(response);

    });
  }

  ngOnInit() {

  }
}
