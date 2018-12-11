import {Component, OnInit} from '@angular/core';
import {LoginGuard} from "../login/login.guard";
import {StorageService} from "../shared/service/storage.service";
import {Router} from "@angular/router";
import {AuthService} from "../shared/service/auth.service";
import {Observable} from "rxjs";
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
  providers: [LoginGuard]
})
export class MainNavComponent implements OnInit {

  public buttonText: string = "Sign In";
  public isLoggedIn: boolean = false;
  public isLoggedIn$: Observable<boolean>;
  public isLoggedInAdmin$: Observable<boolean>;
  private name: string = "";

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(private storage: StorageService,
              private auth: AuthService,
              private route: Router,
              private breakpointObserver: BreakpointObserver) {
  }

  ngOnInit() {
    this.storage.watchStorage().subscribe((data: string) => {
      if (this.isLoggedIn == false) {
        if (this.storage.getItem('admin')) {
          this.name = this.storage.getItem('admin');
          this.isLoggedIn = true;
          this.buttonText = "Sign Out";
          this.isLoggedInAdmin$ = this.auth.isLoggedInAdmin;
        }
        else if (this.storage.getItem('name')) {
          this.name = this.storage.getItem('name');
          this.isLoggedIn = true;
          this.buttonText = "Sign Out";
          this.isLoggedIn$ = this.auth.isLoggedIn;
        }
      } else {
        this.isLoggedIn = false;
        this.buttonText = "Sign In";
      }
    });

  }

  public logInOut() {
    if (this.isLoggedIn === false) {
      this.route.navigateByUrl('/login');
    }
    else {
      this.storage.removeItem('name');
      this.storage.removeItem('admin');
      this.route.navigateByUrl('/');
      this.auth.logout();
      this.name = "";
    }
  }
}
