import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../../shared/service/api.service";
import {SearchuserService} from "../../../shared/service/searchuser.service";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  public showUserForm: FormGroup;
  public usersFound = <object>[];
  public search: FormControl = new FormControl();

  constructor(private api: ApiService,
              private searchUser: SearchuserService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.showUserForm = this.fb.group({
      'email': ['', [Validators.required, Validators.email]]
    });

    this.showUser();
  }

  showUser() {
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

  userSearch(event) {
    console.log(event);
//    this.openDialog(event);
  }

}
