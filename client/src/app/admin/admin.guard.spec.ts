import { TestBed, inject } from '@angular/core/testing';

import { AdminGuard } from './admin.guard';
import {RouterTestingModule} from "@angular/router/testing";
import {ApiService} from "../shared/service/api.service";

describe('AdminGuard', () => {
  let apiService: ApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [AdminGuard, {provide: ApiService}],

    });
  });

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
  });

  it('should ...', inject([AdminGuard], (guard: AdminGuard) => {
    expect(guard).toBeTruthy();
  }));
});
