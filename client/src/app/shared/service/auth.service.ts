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
        this.credentials = Object.values(response.body);
        if ( response.body['role'] === 'Admin' ) {
          this.loggedInAdmin$.next(true);
          this.router.navigateByUrl('/admin');
          this.storage.setItem('email', response.body['email']);
          this.storage.setItem('admin', response.body['username']);
        } else {
          this.loggedIn$.next(true);
          this.router.navigate(['/']);
          this.storage.setItem('email', response.body['email']);
          this.storage.setItem('name', response.body['username']);
        }
        this.logged = true;
        this.failedLogin$.next(false);
        this.storage.setItem('token', response.headers.get('Authorization'));
        this.storage.setItem('userCreated', response.body['userCreated']);
        this.storage.setItem('role', response.body['role']);
      },
      error => {
        this.failedLogin$.next(true);
      }
    );
    return this.logged;
  }

  public logout() {
    this.api.logout().subscribe(response => {
      },
      error => {
      }
    );
    this.storage.removeItem('token');
    this.storage.removeItem("email");
    this.storage.removeItem("name");
    this.loggedIn$.next(false);
    this.loggedInAdmin$.next(false);
    this.router.navigate(['/']);
  }
}
