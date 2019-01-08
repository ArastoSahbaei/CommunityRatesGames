import { TestBed } from '@angular/core/testing';

import { SearchuserService } from './searchuser.service';
import {ApiService} from "./api.service";
import {RouterTestingModule} from "@angular/router/testing";

describe('SearchuserService', () => {
  let apiService: ApiService;

  beforeEach(() => TestBed.configureTestingModule({
    imports: [RouterTestingModule],
    providers: [{provide: ApiService}]
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
  });

  it('should be created', () => {
    const service: SearchuserService = TestBed.get(SearchuserService);
    expect(service).toBeTruthy();
  });
});
