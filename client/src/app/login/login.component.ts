import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../shared/service/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;
  public errorMessage: string = "Sorry, your credentials was not found!";
  public failedLogin: boolean = false;

  constructor(private fb: FormBuilder,
              private auth: AuthService,
              private router: Router) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      'email' : ['', [Validators.required, Validators.email]],
      'password' : ['', Validators.required]
    });
  }

  onSubmit() {

    this.auth.login(this.loginForm.value);

    this.auth.failedLogin.subscribe((data) => {
      if ( data == true ) {
        this.failedLogin = true;
      }
    })
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

  register() {
    this.router.navigate(['/register']);
//    this.router.navigateByUrl('/');
  }
}
