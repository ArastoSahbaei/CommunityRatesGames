import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGameComponent } from './add-game.component';
import { ApiService } from "../../shared/service/api.service";
import { MaterialModule } from "../../shared/material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RouterTestingModule } from "@angular/router/testing";
import {NO_ERRORS_SCHEMA} from "@angular/core";

describe('AddGameComponent', () => {
  let component: AddGameComponent;
  let fixture: ComponentFixture<AddGameComponent>;
  let apiService: ApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MaterialModule, ReactiveFormsModule, FormsModule, RouterTestingModule],
      declarations: [ AddGameComponent ],
      schemas:[NO_ERRORS_SCHEMA],
      providers: [{provide: ApiService}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
    fixture = TestBed.createComponent(AddGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
