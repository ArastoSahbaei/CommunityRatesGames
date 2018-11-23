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
              private storage: StorageService,
              private api: ApiService) {}

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  public login(user : User) {
    console.log(user);
    this.api.checkCredentials(user).subscribe(result => {
      console.log(result)
    },
      error =>{ console.log(error);
      });

  }

  public logout() {
    this.loggedIn.next(false);
    this.router.navigate(['/']);
  }
}
