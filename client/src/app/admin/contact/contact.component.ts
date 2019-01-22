import { Component, OnInit } from '@angular/core';
import { ApiService } from "../../shared/service/api.service";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  mails: object;

  constructor(private api: ApiService) { }

  ngOnInit() {
    this.api.getAdminAllMails().subscribe((response) => {
      this.mails = response;
    });
  }

  onChange(event: Event) {
    const selectedData = {
      value: event.value;
    };
    console.log(event);
  }
}
