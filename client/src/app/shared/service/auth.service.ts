import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {User} from "../interface/user.interface";
import {Router} from "@angular/router";
import {StorageService} from "./storage.service";
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(private router : Router,
              private api: ApiService,
              private storage: StorageService) {}

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  public login(user : User) {
/*
Dont forget to let the return true / false be outside of the observable to avoid errors
 */
    this.api.checkCredentials(user).subscribe(response => {
      console.log(response)
      },
      error => { this.router.navigate(['/error']);
    });


    if ( user.email === "test@test.com" && user.password === "testardetta") {
      this.loggedIn.next(true);
      this.router.navigate(['/']);
      //Change 'Test' to name from backend
      this.storage.setItem('name', 'Test');
    } else {
      return false;
    }
  }

  public logout() {
    this.loggedIn.next(false);
    this.router.navigate(['/']);
  }
}
