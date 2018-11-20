import { Component } from '@angular/core';
import {LoginformGuard} from "../login/loginform/loginform.guard";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
  providers: [LoginformGuard]
})
export class MainNavComponent {

  constructor() {}

}
