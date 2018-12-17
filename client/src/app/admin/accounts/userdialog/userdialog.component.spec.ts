import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserdialogComponent } from './userdialog.component';

describe('UserdialogComponent', () => {
  let component: UserdialogComponent;
  let fixture: ComponentFixture<UserdialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserdialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
