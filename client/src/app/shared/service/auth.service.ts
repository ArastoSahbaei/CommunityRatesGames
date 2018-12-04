import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {User} from "../interface/user.interface";
import {Router} from "@angular/router";
import {StorageService} from "./storage.service";
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn$ = new BehaviorSubject<boolean>(false);
  private credentials: Object;
  private logged: boolean = false;
  private failedLogin$ = new BehaviorSubject<boolean>(false);

  constructor(private router: Router,
              private api: ApiService,
              private storage: StorageService) {
  }

  get isLoggedIn() {
    return this.loggedIn$.asObservable();
  }

  get failedLogin() {
    return this.failedLogin$.asObservable();
  }

  public login(user: User) {

    this.api.checkCredentials(user).subscribe(response => {
        this.credentials = Object.values(response);
        this.loggedIn$.next(true);
        this.router.navigate(['/']);
        this.storage.setItem('name', this.credentials[2]);
        this.logged = true;
        this.failedLogin$.next(false);
      },
      error => {
        this.failedLogin$.next(true);
      }
    );

    return this.logged;
  }

  public logout() {
    this.loggedIn$.next(false);
    this.router.navigate(['/']);
  }
}
