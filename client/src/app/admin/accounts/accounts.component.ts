import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ApiService} from "../../shared/service/api.service";
import {MatDialog, MatDrawer, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {User} from "../../shared/interface/user.interface";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Register} from "../../shared/interface/register.interface";
import {SearchuserService} from "../../shared/service/searchuser.service";
import {DialogComponent} from "../../dialog/dialog.component";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit, AfterViewInit {

  public search: FormControl = new FormControl();
  public usersFound = <object>[];
  private paginator: MatPaginator;
  private sort: MatSort;
  public userForm: FormGroup;
  public showUserForm: FormGroup;
  choice: boolean = false;
  addUser: boolean = false;
  showStatisticOnUser: boolean = false;
  users: User[] = [];
  dataSource = new MatTableDataSource(this.users);
  tableColumns: string[] = ['userName', 'email', 'userCreated'];

  @ViewChild('drawer') drawer: MatDrawer;

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
    this.userForm = this.fb.group({
      'user': ['', Validators.required],
      'email': ['', [Validators.required, Validators.email]],
      'password': ['', Validators.required],
      'role': ['', Validators.required]
    });

    this.showUserForm = this.fb.group({
      'email': ['', [Validators.required, Validators.email]]
    })
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

  addUsers() {
    this.addUser = true;
  }

  reset() {
    this.addUser = false;
    this.choice = false;
    this.showStatisticOnUser = false;
  }

  dashboardSelection(value: number) {
    switch (value) {
      case 1:
        this.reset();
        this.showAllUsers();
        break;
      case 2:
        this.reset();
        this.addUsers();
        break;
      case 3:
        this.reset();
        this.showUser();
        break;
    }
  }

  showUser() {
    this.showStatisticOnUser = true;
    this.search.valueChanges.subscribe(
      user => {
        if ( user != '' ) {
          this.searchUser.searchUser(user).subscribe(
            data => {
              this.usersFound = data as any[];
            })
        }
      });
    this.search.setValue('');
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

  userSearch(event) {
    this.openDialog(event);
  }

  get email() {
    return this.userForm.get('email');
  }

  get password() {
    return this.userForm.get('password');
  }

  get role() {
    return this.userForm.get('role');
  }

  get user() {
    return this.userForm.get('user');
  }

  onSubmit() {
    const user = {} as Register;
    user.email = this.userForm.value.email;
    user.username = this.userForm.value.user;
    user.password = this.userForm.value.password;
    if (this.userForm.value.role == 1) {
      user.role = 'User';
    } else {
      user.role = 'Admin';
    }

    this.api.registerUser(user).subscribe((response) => {
      },
      error => {
        console.log(error);
        throw error;
      }
    );
  }
}
