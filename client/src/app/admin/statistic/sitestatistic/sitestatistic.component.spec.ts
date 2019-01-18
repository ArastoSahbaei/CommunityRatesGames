import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SitestatisticComponent } from './sitestatistic.component';

describe('SitestatisticComponent', () => {
  let component: SitestatisticComponent;
  let fixture: ComponentFixture<SitestatisticComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SitestatisticComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SitestatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
