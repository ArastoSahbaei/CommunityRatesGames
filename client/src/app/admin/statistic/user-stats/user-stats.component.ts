import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ApiService} from "../../../shared/service/api.service";
import {MatDialog, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {User} from "../../../shared/interface/user.interface";

@Component({
  selector: 'app-user-stats',
  templateUrl: './user-stats.component.html',
  styleUrls: ['./user-stats.component.css']
})
export class UserStatsComponent implements OnInit, AfterViewInit {

  users: User[] = [];
  dataSource = new MatTableDataSource(this.users);
  tableColumns: string[] = ['userName', 'email', 'userCreated'];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private api: ApiService,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.showAllUsers();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  showAllUsers() {
    this.api.getAllUsers().subscribe((data) => {
      this.users = Object.values(data);
      this.dataSource.data = this.users;
    }, error => {
      throw error;
    });
  }
}
