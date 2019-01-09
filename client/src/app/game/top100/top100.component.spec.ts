import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Top100Component } from './top100.component';
import {ApiService} from "../../shared/service/api.service";
import {MaterialModule} from "../../shared/material/material.module";
import {ReactiveFormsModule} from "@angular/forms";

describe('Top100Component', () => {
  let component: Top100Component;
  let fixture: ComponentFixture<Top100Component>;
  let apiService: ApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MaterialModule, ReactiveFormsModule],
      declarations: [ Top100Component ],
      providers: [{provide: ApiService}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
    fixture = TestBed.createComponent(Top100Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
