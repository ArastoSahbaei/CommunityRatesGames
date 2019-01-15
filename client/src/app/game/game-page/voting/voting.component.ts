import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../../shared/service/api.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Voting} from "../../../shared/interface/voting.interface";
import {StorageService} from "../../../shared/service/storage.service";
import {ActivatedRoute} from "@angular/router";


@Component({
  selector: 'app-voting',
  templateUrl: './voting.component.html',
  styleUrls: ['./voting.component.css']
})

export class VotingComponent implements OnInit {
  ratings : FormGroup
  response: any = {};
  max: number = 10;
  star: number;

  constructor(private api: ApiService, private formBuilder: FormBuilder,
              private storage: StorageService, private route: ActivatedRoute)
  {
    this.getTheGames();
  }


  ngOnInit() {
    this.ratings = this.formBuilder.group({
      'game': ['', [Validators.required]],
      'user': ['', Validators.required],
      'rating': [, Validators.required]
    });
  }

  getTheGames(){
    this.route.queryParams.subscribe(queryParam => {
      this.api.getOneGameByTitle(queryParam.title).subscribe(response =>{
        console.log(response);
        this.response = response;
      });
    });
  }


  addRating(star: number) {
    const rate = {} as Voting;
    rate.rating = this.star;
    rate.user = this.storage.getItem('name');
    rate.game = this.response.title;

    console.log(rate)

    this.api.postRating(rate).subscribe((response) => {
        console.log(response);
      }, error => {
        throw error;
      });
    }

  hoveringOver(value: number): void {
    this.star = value;
    console.log(this.star)
    this.addRating(this.star);
  }

  get rating(){
    return this.ratings.get('rating')
  }
}
