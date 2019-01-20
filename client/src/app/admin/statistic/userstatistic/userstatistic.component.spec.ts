import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserstatisticComponent } from './userstatistic.component';

describe('UserstatisticComponent', () => {
  let component: UserstatisticComponent;
  let fixture: ComponentFixture<UserstatisticComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserstatisticComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserstatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
