import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {User} from "../interface/user.interface";
import {Router} from "@angular/router";
import {StorageService} from "./storage.service";
import {ApiService} from "./api.service";
import {Storage} from "../interface/storage.interface";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedInAdmin$ = new BehaviorSubject<Storage["admin"]>(false);
  private loggedIn$ = new BehaviorSubject<Storage["name"]>(false);
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

  get isLoggedInAdmin() {
    return this.loggedInAdmin$.asObservable();
  }

  get failedLogin() {
    return this.failedLogin$.asObservable();
  }

  public login(user: User) {

    this.api.checkCredentials(user).subscribe(response => {
        this.credentials = Object.values(response);
        if ( this.credentials[5] === 'admin' ) {
         this.loggedInAdmin$.next(true);
         this.router.navigate(['/admin']);
         this.storage.setItem('admin', this.credentials[2]);
         this.logged = true;
         this.failedLogin$.next(false);
        } else {
          this.loggedIn$.next(true);
          this.router.navigate(['/']);
          this.storage.setItem('name', this.credentials[2]);
          this.logged = true;
          this.failedLogin$.next(false);
        }
      },
      error => {
        this.failedLogin$.next(true);
      }
    );
    return this.logged;
  }

  public logout() {
    this.loggedIn$.next(false);
    this.loggedInAdmin$.next(false);
    this.router.navigate(['/']);
  }
}
