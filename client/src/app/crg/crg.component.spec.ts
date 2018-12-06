import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrgComponent } from './crg.component';

describe('CrgComponent', () => {
  let component: CrgComponent;
  let fixture: ComponentFixture<CrgComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrgComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
