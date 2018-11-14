import { Component, OnInit } from '@angular/core';
import {HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {


  constructor(private http : HttpClientModule) {

  }

  ngOnInit() {
  }
}
