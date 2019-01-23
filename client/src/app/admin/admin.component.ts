import { Component, OnInit } from '@angular/core';
import {ApiService} from "../shared/service/api.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  private mails: object;
  amountOfMails: number;

  constructor(private api: ApiService) {
    this.mails = this.api.getAdminAllMails().subscribe((response => {
      this.amountOfMails = Object.keys(response).length;
      console.log(response);
    }));
  }

  ngOnInit() {
  }
}
