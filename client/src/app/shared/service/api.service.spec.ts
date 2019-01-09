import { TestBed } from '@angular/core/testing';

import { ApiService } from './api.service';
import {MaterialModule} from "../material/material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";

describe('ApiService', () => {
  let apiService: ApiService;

  beforeEach(() => TestBed.configureTestingModule({
    imports: [MaterialModule, ReactiveFormsModule, RouterTestingModule],
    providers: [{provide: ApiService}]
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
  });
});
