import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchgameComponent } from './searchgame.component';

describe('SearchgameComponent', () => {
  let component: SearchgameComponent;
  let fixture: ComponentFixture<SearchgameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchgameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchgameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
