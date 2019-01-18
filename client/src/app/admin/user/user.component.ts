import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ApiService} from "../../shared/service/api.service";
import {MatDialog, MatDrawer, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {User} from "../../shared/interface/user.interface";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Register} from "../../shared/interface/register.interface";
import {SearchuserService} from "../../shared/service/searchuser.service";
import {DialogComponent} from "../dialog/dialog.component";
import {UserdialogComponent} from "./userdialog/userdialog.component";

@Component({
  selector: 'app-accounts',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit{

  constructor(
              private dialog: MatDialog) {
  }

  ngOnInit() {



  }



}
