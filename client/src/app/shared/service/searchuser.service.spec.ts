import { TestBed } from '@angular/core/testing';

import { SearchuserService } from './searchuser.service';

describe('SearchuserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchuserService = TestBed.get(SearchuserService);
    expect(service).toBeTruthy();
  });
});
