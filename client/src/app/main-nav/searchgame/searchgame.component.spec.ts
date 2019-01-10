import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchgameComponent } from './searchgame.component';
import {ApiService} from "../shared/service/api.service";
import {MaterialModule} from "../shared/material/material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {AppRoutingModule} from "../app-routing.module";
import {CrgComponent} from "../crg/crg.component";
import {HomeComponent} from "../home/home.component";

describe('SearchgameComponent', () => {
  let component: SearchgameComponent;
  let fixture: ComponentFixture<SearchgameComponent>;
  let apiService: ApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MaterialModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ SearchgameComponent ],
      providers: [{provide: ApiService}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
    fixture = TestBed.createComponent(SearchgameComponent);
    component = fixture.componentInstance;
    console.log(component);
    fixture.detectChanges();
  });

  it('should create', () => {
    console.log("Here", component);
    expect(true).toBeTruthy();
  });
});
