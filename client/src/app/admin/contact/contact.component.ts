import { Component, OnInit } from '@angular/core';
import { ApiService } from "../../shared/service/api.service";
import { MatCheckboxChange, MatOptionSelectionChange } from "@angular/material";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Reply } from "../../shared/interface/reply.interface";
import { StorageService } from "../../shared/service/storage.service";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  public message: FormGroup;
  public mails: object;
  public selectedMail: object;

  constructor(private storage: StorageService,
              private api: ApiService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.api.getAdminAllMails().subscribe((response) => {
      this.mails = response;
    });

    this.message = this.fb.group({
      'userMessage': ['', [Validators.required]],
      'user': [{value: '', disabled: true}, [Validators.required]],
      'answer': ['', Validators.required],
      'flagged': [false],
      'admin':[{value: '', disabled: true}, [Validators.required, Validators.email]],
    });
    this.message.controls['userMessage'].disable();
  }

  onChange(event: MatOptionSelectionChange, mail: object) {
    if(event.source.selected) {
      this.selectedMail = mail;
    }
    this.message.patchValue({
      user: this.selectedMail['email'],
      userMessage: this.selectedMail['userMessage']
    });
  }

  toggle(event: MatCheckboxChange) {
    if (event.checked === true){
      this.message.controls['admin'].enable();
    } else {
      this.message.controls['admin'].disable();
    }
  }

  submit() {
    const reply = {} as Reply;
    reply.id = this.selectedMail['id'];
    reply.responseMessage = this.message.value['answer'];
    reply.urgent = this.message.value['flagged'];
    reply.administredBy = this.storage.getItem('email');
    reply.seen = true;
    reply.flaggedForAdmin = this.message.value['admin'];


    this.api.answerUserMail(reply).subscribe((response) => {
      this.message.reset();
    }, error => {
      throw error;
    });
  }

  get answer() {
    return this.message.get('answer');
  }
}
