import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import {MaterialModule} from "./shared/material/material.module";

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
      providers: [MaterialModule]
    }).compileComponents();
  }));
});
