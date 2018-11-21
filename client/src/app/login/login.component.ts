import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StorageService} from "../shared/service/storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;
  public errorMessage: string = "Sorry, your credentials was not found!";
  public isLoggedIn: boolean = false;

  constructor(private fb: FormBuilder,
              private storage: StorageService) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      'email' : ['', [Validators.required, Validators.email]],
      'password' : ['', Validators.required]
    });
  }

  onSubmit() {
    let user = this.loginForm.value.email;
    let pass = this.loginForm.value.password;
    if ( user === 'test@test.com' && pass === 'test') {
      this.storage.setItem('name', 'Test');
    }
    else {
      this.isLoggedIn = true;
    }
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }
}
