import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from "@angular/forms";
import { ApiService } from "../../shared/service/api.service";
import {Register} from "../../shared/interface/register.interface";
import {AuthService} from "../../shared/service/auth.service";
import {User} from "../../shared/interface/user.interface";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public registerForm: FormGroup;

  constructor(private fb: FormBuilder,
              private api: ApiService,
              private auth: AuthService,
              private router: Router) { }

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
    user.userName = this.registerForm.value.user;
    user.password = this.registerForm.value.password;

    this.api.registerUser(user).subscribe(response => {
      console.log(response) },
      error => { this.router.navigate(['/error']);
    });
    if ( this.registerForm.value.login === true ) {
      const user = {} as User;
      user.email = this.registerForm.value.email;
      user.password = this.registerForm.value.password;
      this.auth.login(user);
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
