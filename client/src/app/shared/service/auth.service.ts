import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {User} from "../interface/user.interface";
import {Router} from "@angular/router";
import {StorageService} from "./storage.service";
import {ApiService} from "./api.service";
import {Storage} from "../interface/storage.interface";
import {flatMap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedInAdmin$ = new BehaviorSubject<Storage["admin"]>(false);
  private loggedIn$ = new BehaviorSubject<Storage["name"]>(false);
  private credentials: Object;
  private logged: boolean = false;
  private failedLogin$ = new BehaviorSubject<boolean>(false);
  private authToken$ = new BehaviorSubject<string>(null);
  private static token: string

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

  get authToken() {
    return this.authToken$.asObservable();
  }

  public login(user: User) {
    this.api.checkCredentials(user).pipe(
      flatMap(response => {
        AuthService.token = response['token'];
        this.authToken$.next(response['token']);
        return this.authToken$.asObservable();
      }),
      flatMap(token => {
        return this.api.getActiveUser(token);
      })
    ).subscribe(response => {
      console.log(response);
      this.credentials = Object.values(response);
      if ( this.credentials[4] === 'admin' ) {
        this.loggedInAdmin$.next(true);
        this.router.navigate(['/admin']);
        this.storage.setItem('admin', response['userName']);
      } else {
        this.loggedIn$.next(true);
        this.router.navigate(['/']);
        this.storage.setItem('name', response['userName']);
      }
      this.logged = true;
      this.failedLogin$.next(false);
    },
    error => {
      this.failedLogin$.next(true);
    });
    return this.logged;
  }

  public logout() {
    this.authToken$.next(null);
    this.api.destroyToken(AuthService.token).subscribe(response => {
      this.loggedIn$.next(false);
      this.loggedInAdmin$.next(false);
      this.router.navigate(['/']);
      AuthService.token = null;
    });
  }
}
