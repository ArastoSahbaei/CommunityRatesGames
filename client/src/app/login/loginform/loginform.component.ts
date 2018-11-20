import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-loginform',
  templateUrl: './loginform.component.html',
  styleUrls: ['./loginform.component.css']
})
export class LoginformComponent implements OnInit {

  public loginForm: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      'email' : new FormControl('', [Validators.required, Validators.email]),
      'password' : new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    console.log("Hej");
    let user = this.loginForm.value.email;
    let pass = this.loginForm.value.password;
    console.log(user + ' ' + pass);
    if ( user === 'test@test.com' && pass === 'test') {
      console.log("true");
    }
    else {
      console.log("false");
    }
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }
}
