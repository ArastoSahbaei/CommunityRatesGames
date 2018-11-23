import {Component, OnInit} from '@angular/core';
import {LoginGuard} from "../login/login.guard";
import {StorageService} from "../shared/service/storage.service";
import {Router} from "@angular/router";
import {AuthService} from "../shared/service/auth.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
  providers: [LoginGuard]
})
export class MainNavComponent implements OnInit{

  private name: string = "";
  public buttonText: string = "Sign In";
  public isLoggedIn: boolean = false;
  public isLoggedIn$: Observable<boolean>;

  constructor(private storage: StorageService,
              private auth: AuthService,
              private route: Router) {}

  ngOnInit() {
    this.storage.watchStorage().subscribe((data:string) => {
      if ( this.isLoggedIn == false ) {
        this.name = this.storage.getItem('name');
        this.isLoggedIn = true;
        this.buttonText = "Sign Out";
      } else {
        this.isLoggedIn = false;
        this.buttonText = "Sign In";
      }
    });
    this.isLoggedIn$ = this.auth.isLoggedIn;
  }

  public logInOut() {
    if (this.isLoggedIn === false ) {
      this.route.navigateByUrl('/login');
    }
    else {
      this.storage.removeItem('name');
      this.route.navigateByUrl('/');
      this.auth.logout();
      this.name = "";
    }
  }
}
