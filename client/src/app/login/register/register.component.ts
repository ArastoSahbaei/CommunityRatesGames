import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from "@angular/forms";
import { ApiService } from "../../shared/service/api.service";
import {Register} from "../../shared/interface/register.interface";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public registerForm: FormGroup;

  constructor(private fb: FormBuilder,
              private api: ApiService) { }

  ngOnInit() {
    this.registerForm = this.fb.group({
      'user' : ['', [Validators.required]],
      'email' : ['', [Validators.required, Validators.email]],
      'password' : ['', [Validators.required, Validators.minLength,Validators.maxLength]],
      'login' : []
    });
  }

  register() {
    const user = {} as Register;
    user.email = this.registerForm.value.email;
    user.username = this.registerForm.value.user;
    user.password = this.registerForm.value.password;

    this.api.registerUser(user).subscribe((response) => {
      console.log(response);
    });
    if ( this.registerForm.value.login === true ) {
      console.log("login");
    }
  }

  get email() {
    return this.registerForm.get('email');
  }

  get user() {
    return this.registerForm.get('user');
  }

  get password() {
    return this.registerForm.get('password');
  }

}
