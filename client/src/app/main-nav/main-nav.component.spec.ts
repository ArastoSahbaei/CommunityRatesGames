import { LayoutModule } from '@angular/cdk/layout';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';


import { MainNavComponent } from './main-nav.component';
import { MaterialModule } from "../shared/material/material.module";
import { ApiService } from "../shared/service/api.service";
import { ReactiveFormsModule } from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {SearchgameComponent} from "./searchgame/searchgame.component";

describe('MainNavComponent', () => {
  let component: MainNavComponent;
  let fixture: ComponentFixture<MainNavComponent>;
  let apiService: ApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MainNavComponent,
        SearchgameComponent],
      imports: [
        NoopAnimationsModule,
        LayoutModule,
        MaterialModule,
        ReactiveFormsModule,
        RouterTestingModule
      ],
      providers: [{provide: ApiService}]
    }).compileComponents();
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService)
    fixture = TestBed.createComponent(MainNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
