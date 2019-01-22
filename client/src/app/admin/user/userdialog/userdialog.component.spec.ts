import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserdialogComponent } from './userdialog.component';
import {ApiService} from "../../../shared/service/api.service";
import {MaterialModule} from "../../../shared/material/material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {of} from "rxjs";

describe('UserdialogComponent', () => {
  let component: UserdialogComponent;
  let fixture: ComponentFixture<UserdialogComponent>;
  let apiService: ApiService;
  let dialog: MatDialog;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MaterialModule, ReactiveFormsModule],
      declarations: [ UserdialogComponent ],
      providers: [{provide: ApiService},
        {provide: MatDialogRef, useValue: {}},
        {provide: MAT_DIALOG_DATA, useValue: {}
        }]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    apiService = TestBed.get(ApiService);
    dialog = TestBed.get(MatDialog);
    fixture = TestBed.createComponent(UserdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
