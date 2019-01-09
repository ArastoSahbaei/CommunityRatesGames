import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlatformComponent } from './platform.component';
import {ApiService} from "../../shared/service/api.service";
import {MaterialModule} from "../../shared/material/material.module";
import {ReactiveFormsModule} from "@angular/forms";

describe('PlatformComponent', () => {
  let component: PlatformComponent;
  let fixture: ComponentFixture<PlatformComponent>;
  let apiService: ApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MaterialModule, ReactiveFormsModule],
      declarations: [ PlatformComponent ],
      providers: [{provide: ApiService}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
    fixture = TestBed.createComponent(PlatformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
