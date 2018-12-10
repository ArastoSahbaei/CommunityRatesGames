import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../shared/service/api.service";
import {Contact} from "../../shared/interface/contact.interface";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  contact: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private api: ApiService) {}

  ngOnInit() {
    this.contact = this.formBuilder.group({
      'email': ['', [Validators.required, Validators.email]],
      'message': ['', Validators.required]
    });
  }

  get email(){
    return this.contact.get('email')
  }

  get message(){
   return this.contact.get('message')
  }

  onSubmit() {
    const contactMessage = {
      email : this.contact.value.email,
      message : this.contact.value.message
    };

    this.api.addNewContactMessage(contactMessage);
  }
}
