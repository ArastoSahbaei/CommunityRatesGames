import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GamePageComponent } from './game-page.component';
import {MaterialModule} from "../../shared/material/material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ApiService} from "../../shared/service/api.service";
import {ActivatedRoute} from "@angular/router";
import {of} from "rxjs";

describe('GamePageComponent', () => {
  let component: GamePageComponent;
  let fixture: ComponentFixture<GamePageComponent>;
  let apiService: ApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MaterialModule, ReactiveFormsModule],
      declarations: [ GamePageComponent ],
      providers: [{provide: ApiService}, {provide: ActivatedRoute, useValue: {params: of({id: 13})} }]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
    fixture = TestBed.createComponent(GamePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
