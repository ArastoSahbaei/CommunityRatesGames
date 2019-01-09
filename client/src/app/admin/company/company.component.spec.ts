import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyComponent } from './company.component';
import {MaterialModule} from "../../shared/material/material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ApiService} from "../../shared/service/api.service";

describe('CompanyComponent', () => {
  let component: CompanyComponent;
  let fixture: ComponentFixture<CompanyComponent>;
  let apiService: ApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MaterialModule, ReactiveFormsModule],
      declarations: [ CompanyComponent ],
      providers: [{provide: ApiService}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
    fixture = TestBed.createComponent(CompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
