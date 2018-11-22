import { TestBed } from '@angular/core/testing';

import { SearchgameService } from './searchgame.service';

describe('SearchgameService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchgameService = TestBed.get(SearchgameService);
    expect(service).toBeTruthy();
  });
});
