import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  contact: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit() {
    this.contact = this.formBuilder.group({
      'email': ['', [Validators.required, Validators.email]],
      'messages': ['', Validators.required]
    });
  }

  get email(){
    return this.contact.get('email')
  }

  get message(){
   return this.contact.get('messages')
  }
}
