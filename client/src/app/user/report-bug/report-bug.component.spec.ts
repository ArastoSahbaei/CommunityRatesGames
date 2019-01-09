import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportBugComponent } from './report-bug.component';
import {ApiService} from "../../shared/service/api.service";
import {MaterialModule} from "../../shared/material/material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";

describe('ReportBugComponent', () => {
  let component: ReportBugComponent;
  let fixture: ComponentFixture<ReportBugComponent>;
  let apiService: ApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MaterialModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ ReportBugComponent ],
      providers: [{provide: ApiService}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
    fixture = TestBed.createComponent(ReportBugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
