import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../shared/service/api.service";
import {Report} from "../../shared/interface/report.interface";

@Component({
  selector: 'app-report-bug',
  templateUrl: './report-bug.component.html',
  styleUrls: ['./report-bug.component.css']
})
export class ReportBugComponent implements OnInit {

  report: FormGroup;

  constructor(private formBuilder: FormBuilder, private api: ApiService) {}

  ngOnInit() {
    this.report = this.formBuilder.group({
      'email':['',[Validators.required, Validators.email]],
      'username':['', Validators.required],
      'message':['', Validators.required]
    });
  }

  onSubmit() {
    const reportBugMessage = {} as Report;

    reportBugMessage.email = this.report.value.email;
    reportBugMessage.username = this.report.value.username;
    reportBugMessage.message = this.report.value.message;

    this.api.reportBug(reportBugMessage)
      .subscribe((response) => {
        alert("Thank you for report this bug!");
        this.report.reset();
      });
  }

  get email(){
    return this.report.get('email')
  }

  get message(){
    return this.report.get('message')
  }

  get username(){
    return this.report.get('username')
  }

}
