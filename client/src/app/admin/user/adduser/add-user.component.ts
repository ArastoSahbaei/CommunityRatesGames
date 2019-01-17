import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../../shared/service/api.service";
import {SearchuserService} from "../../../shared/service/searchuser.service";
import {Register} from "../../../shared/interface/register.interface";

@Component({
  selector: 'app-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  public userForm: FormGroup;

  constructor(private api: ApiService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.userForm = this.fb.group({
      'user': ['', Validators.required],
      'email': ['', [Validators.required, Validators.email]],
      'password': ['', Validators.required],
      'role': ['', Validators.required]
    })
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
    }
    else if (this.userForm.value.role == 3) {
      user.role = 'Moderator';
    }
    else {
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
