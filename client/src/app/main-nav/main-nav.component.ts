import {Component, OnInit} from '@angular/core';
import {LoginformGuard} from "../login/loginform/loginform.guard";
import {StorageService} from "../shared/service/storage.service";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
  providers: [LoginformGuard]
})
export class MainNavComponent implements OnInit{

  private name: string = "";
  public buttonText: boolean = false;

  constructor(private storage: StorageService) {}

  ngOnInit() {
    this.storage.watchStorage().subscribe((data:string) => {
      this.name = this.storage.getItem('name');
      this.buttonText = true;
    });
  }
}
