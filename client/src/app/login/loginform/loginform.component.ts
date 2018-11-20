import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {StorageService} from "../../shared/service/storage.service";

@Component({
  selector: 'app-loginform',
  templateUrl: './loginform.component.html',
  styleUrls: ['./loginform.component.css']
})
export class LoginformComponent implements OnInit {

  public loginForm: FormGroup;

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
