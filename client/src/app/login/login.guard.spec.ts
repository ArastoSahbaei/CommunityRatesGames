import { TestBed, async, inject } from '@angular/core/testing';

import { LoginGuard } from './login.guard';
import {RouterTestingModule} from "@angular/router/testing";
import {ApiService} from "../shared/service/api.service";

describe('LoginGuard', () => {
  let apiService: ApiService;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [LoginGuard, {provide: ApiService}],
    });
  });

  it('should ...', inject([LoginGuard], (guard: LoginGuard) => {
    expect(guard).toBeTruthy();
  }));
});
