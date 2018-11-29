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
  private credentials : Object;
  private logged: boolean = false;

  constructor(private router : Router,
              private api: ApiService,
              private storage: StorageService) {}

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  public login(user : User) {

    this.api.checkCredentials(user).subscribe(response => {
      if (response == undefined || null ) {
        this.router.navigate(['/error']);
      } else {
          this.credentials = Object.values(response);
          this.loggedIn.next(true);
          this.router.navigate(['/']);
          this.storage.setItem('name', this.credentials[2]);
          this.logged = true;
        }
      },
      error => {
        console.log(error);
        this.router.navigate(['/error']);
      }
    );

    return this.logged;
  }

  public logout() {
    this.loggedIn.next(false);
    this.router.navigate(['/']);
  }
}
