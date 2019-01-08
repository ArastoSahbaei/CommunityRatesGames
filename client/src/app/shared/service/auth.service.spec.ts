import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';
import {MaterialModule} from "../material/material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {ApiService} from "./api.service";

describe('AuthService', () => {
  let apiService: ApiService;

  beforeEach(() => TestBed.configureTestingModule({
    imports: [MaterialModule, ReactiveFormsModule, RouterTestingModule],
    providers: [{provide: ApiService}]
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
  });

  it('should be created', () => {
    const service: AuthService = TestBed.get(AuthService);
    expect(service).toBeTruthy();
  });
});
