import {Component, OnInit} from '@angular/core';
import {LoginGuard} from "../login/login.guard";
import {StorageService} from "../shared/service/storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
  providers: [LoginGuard]
})
export class MainNavComponent implements OnInit{

  private name: string = "";
  public buttonText: string = "Log In";

  constructor(private storage: StorageService,
              private route: Router) {}

  ngOnInit() {
    this.storage.watchStorage().subscribe((data:string) => {
      this.checkLoggedIn();
    });
  }

  public logInOut() {
    if (this.buttonText === "Log In" ) {
      this.route.navigateByUrl('/login');
    }
    else {
      this.storage.removeItem('name');
    }
  }

  private checkLoggedIn() {
    if ( this.buttonText === "Log In" ) {
      this.name = this.storage.getItem('name');
      this.buttonText = "Sign Out";
    } else {
      this.name = "";
      this.buttonText = "Sign In";
    }
  }
}
