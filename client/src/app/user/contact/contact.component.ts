import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../shared/service/api.service";
import {Contact} from "../../shared/interface/contact.interface";
import {StorageService} from "../../shared/service/storage.service";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  contact: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private api: ApiService,
              private storage: StorageService) {}

  ngOnInit() {
    this.contact = this.formBuilder.group({
      'message': ['', Validators.required]
    });
  }

  get message(){
   return this.contact.get('message')
  }

  onSubmit() {
    const contactMessage = {} as Contact;

    contactMessage.email = this.storage.getItem("email");
    contactMessage.userMessage = this.contact.value.message;

    this.api.addNewContactMessage(contactMessage)
      .subscribe((response) => {
        this.contact.reset();
      });
  }
}
