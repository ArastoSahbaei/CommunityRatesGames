import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Top100Component } from './top100.component';

describe('Top100Component', () => {
  let component: Top100Component;
  let fixture: ComponentFixture<Top100Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Top100Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Top100Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
