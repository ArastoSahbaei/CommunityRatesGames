import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {User} from "../interface/user.interface";
import {Router} from "@angular/router";
import {StorageService} from "./storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(private router : Router,
              private storage: StorageService) {}

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  public login(user : User) {
    if ( user.email === "test@test.com" && user.password === "test") {
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
