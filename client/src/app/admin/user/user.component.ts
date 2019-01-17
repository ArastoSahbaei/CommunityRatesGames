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
export class UserComponent implements OnInit, AfterViewInit {



  private paginator: MatPaginator;
  private sort: MatSort;


  choice: boolean = false;
  users: User[] = [];
  dataSource = new MatTableDataSource(this.users);
  tableColumns: string[] = ['userName', 'email', 'userCreated'];

//  @ViewChild('drawer') drawer: MatDrawer;

  @ViewChild(MatPaginator) set matPaginator(mp: MatPaginator) {
    this.paginator = mp;
    this.setDataSourceAttributes();
  }

  @ViewChild(MatSort) set matSort(ms: MatSort) {
    this.sort = ms;
    this.setDataSourceAttributes();
  }

  setDataSourceAttributes() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  constructor(private api: ApiService,
              private searchUser: SearchuserService,
              private fb: FormBuilder,
              private dialog: MatDialog) {
  }

  ngOnInit() {



  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  showAllUsers() {
    this.choice = true;
    this.api.getAllUsers().subscribe((data) => {
      this.users = Object.values(data);
      this.dataSource.data = this.users;
    }, error => {
      throw error;
    });
  }


  openDialog(data: string): void {

    const dialogRef = this.dialog.open(DialogComponent, {
      width: '550px',
      height: '500px',
      data: {user: data}
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openDetails(event: string) {
    const dialogRef = this.dialog.open(UserdialogComponent, {
      width: '550px',
      height: '500px',
      data: {user: event}
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }
}
