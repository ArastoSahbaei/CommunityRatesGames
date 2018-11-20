import { TestBed, async, inject } from '@angular/core/testing';

import { LoginformGuard } from './loginform.guard';

describe('LoginformGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoginformGuard]
    });
  });

  it('should ...', inject([LoginformGuard], (guard: LoginformGuard) => {
    expect(guard).toBeTruthy();
  }));
});
