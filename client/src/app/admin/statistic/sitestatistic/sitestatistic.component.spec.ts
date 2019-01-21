import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteStatisticComponent } from './sitestatistic.component';

describe('SitestatisticComponent', () => {
  let component: SiteStatisticComponent;
  let fixture: ComponentFixture<SiteStatisticComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiteStatisticComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
